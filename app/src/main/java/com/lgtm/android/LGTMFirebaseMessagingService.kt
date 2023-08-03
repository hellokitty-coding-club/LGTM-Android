package com.lgtm.android

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.lgtm.android.common_ui.R
import com.lgtm.domain.firebase.LgtmMessagingService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LGTMFirebaseMessagingService : FirebaseMessagingService(), LgtmMessagingService {
    @Inject
    lateinit var firebaseTokenManager: FirebaseTokenManager

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        firebaseTokenManager.patchFcmToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage.data.isNotEmpty()) {
            // data 확인
        }

        remoteMessage.notification?.let {
            generateNotification(it.title!!, it.body!!)
        }
    }

    private fun generateNotification(
        title: String?,
        message: String?,
        image: Bitmap? = null,
    ) {
        val requestCode = System.currentTimeMillis().toInt()

        val builder = NotificationCompat.Builder(this, channelID)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
//            .setColor(Color.argb(255, 133, 120, 255))   // havit_purple
            .setAutoCancel(true)
            .setContentTitle(title)
            .setLargeIcon(image)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationChannel =
            NotificationChannel(channelID, channelUserNotice, NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(notificationChannel)
        notificationManager.notify(requestCode, builder.build())
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
