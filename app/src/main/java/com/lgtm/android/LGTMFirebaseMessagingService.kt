package com.lgtm.android

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.lgtm.android.common_ui.R
import com.lgtm.android.main.MainActivity
import com.lgtm.android.manage_mission.dashboard.DashboardActivity
import com.lgtm.android.manage_mission.ping_pong_junior.PingPongJuniorActivity
import com.lgtm.domain.constants.ProcessState.CODE_REVIEW
import com.lgtm.domain.constants.ProcessState.MISSION_FINISHED
import com.lgtm.domain.constants.ProcessState.MISSION_PROCEEDING
import com.lgtm.domain.constants.ProcessState.PAYMENT_CONFIRMATION
import com.lgtm.domain.constants.ProcessState.WAITING_FOR_PAYMENT
import com.lgtm.domain.constants.ProcessState.valueOf
import com.lgtm.domain.firebase.LgtmMessagingService
import com.lgtm.domain.usecase.DeviceTokenManagerUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LGTMFirebaseMessagingService : FirebaseMessagingService(), LgtmMessagingService {
    @Inject
    lateinit var deviceTokenManagerUseCase: DeviceTokenManagerUseCase

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        deviceTokenManagerUseCase.patchFcmToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d(TAG, "onMessageReceived: ${remoteMessage.data}")

        if (remoteMessage.data.isNotEmpty()) {
            handleDataType(remoteMessage.data)
        }

        remoteMessage.notification?.let {
            handleNotificationType(it)
        }
    }

    private fun handleDataType(data: Map<String, String>) {
        val requestCode = System.currentTimeMillis().toInt()

        val title = data["title"]
        val body = data["body"]
        val missionId: Int? = data["missionId"]?.toInt()
        val processState = data["pushCategory"]?.let { valueOf(it) }
        val intent = when (processState) {
            WAITING_FOR_PAYMENT, PAYMENT_CONFIRMATION, CODE_REVIEW ->
                Intent(this, DashboardActivity::class.java)
                    .putExtra(DashboardActivity.MISSION_ID, missionId)

            MISSION_PROCEEDING, MISSION_FINISHED ->
                Intent(this, PingPongJuniorActivity::class.java)
                    .putExtra(DashboardActivity.MISSION_ID, missionId)

            else -> return
        }

        val mainIntent = Intent(this, MainActivity::class.java)

        val stackBuilder = TaskStackBuilder.create(this)
            .addParentStack(MainActivity::class.java)
            .addNextIntent(mainIntent)
            .addNextIntent(intent)

        val resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE)

        val builder = createNotificationBuilder(title, body, resultPendingIntent)
        notifyWithChannel(requestCode, builder)
    }

    private fun createNotificationBuilder(
        title: String?,
        message: String?,
        contentIntent: PendingIntent?,
    ): NotificationCompat.Builder {
        return NotificationCompat.Builder(this, channelID)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)
            .setContentTitle(title)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setContentIntent(contentIntent)
    }

    private fun notifyWithChannel(requestCode: Int, builder: NotificationCompat.Builder) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationChannel =
            NotificationChannel(channelID, channelUserNotice, NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(notificationChannel)
        notificationManager.notify(requestCode, builder.build())
    }

    private fun handleNotificationType(notification: RemoteMessage.Notification) {
        val title = notification.title ?: ""
        val message = notification.body ?: ""
        val requestCode = System.currentTimeMillis().toInt()

        val builder = createNotificationBuilder(title, message, null)
        notifyWithChannel(requestCode, builder)
    }

    override fun getDeviceToken(tokenCallBack: (String?) -> Unit) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.e("TokenTest", "Fetching FCM registration token failed", task.exception)
                tokenCallBack(null)
            } else {
                val token = task.result
                tokenCallBack(token)
            }
        }
    }

    companion object {
        const val channelID = "LGTM Notice"
        const val channelNotice = "공지사항"
        const val channelUserNotice = "사용자 알림"
    }
}
