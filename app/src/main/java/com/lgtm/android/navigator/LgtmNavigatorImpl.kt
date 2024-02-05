package com.lgtm.android.navigator

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.lgtm.android.auth.ui.SignInActivity
import com.lgtm.android.common_ui.navigator.LgtmNavigator
import com.lgtm.android.create_mission.CreateMissionActivity
import com.lgtm.android.main.MainActivity
import com.lgtm.android.main.notification.NotificationCenterActivity
import com.lgtm.android.manage_mission.dashboard.DashboardActivity
import com.lgtm.android.manage_mission.ping_pong_junior.PingPongJuniorActivity
import com.lgtm.android.mission_detail.MissionDetailActivity
import com.lgtm.android.mission_suggestion.ui.dashboard.SuggestionDashboardActivity
import com.lgtm.android.mission_suggestion.ui.detail.SuggestionDetailActivity
import com.lgtm.android.profile.ProfileActivity
import javax.inject.Inject

class LgtmNavigatorImpl @Inject constructor(
) : LgtmNavigator {
    override fun navigateToSignIn(context: Context) {
        val intent = Intent(context, SignInActivity::class.java)
        context.startActivity(intent)
    }

    override fun navigateToMain(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }

    override fun navigateToCreateMission(context: Context) {
        val intent = Intent(context, CreateMissionActivity::class.java)
        context.startActivity(intent)
    }

    override fun navigateToMissionDetail(context: Context, missionId: Int) {
        val intent = Intent(context, MissionDetailActivity::class.java).apply {
            putExtra(MissionDetailActivity.MISSION_ID, missionId)
        }
        context.startActivity(intent)
    }

    override fun navigateToDashboard(context: Context, missionId: Int) {
        val intent = Intent(context, DashboardActivity::class.java).apply {
            putExtra(DashboardActivity.MISSION_ID, missionId)
        }
        context.startActivity(intent)
    }

    override fun navigateToProfile(context: Context, userId: Int?) {
        val intent = Intent(context, ProfileActivity::class.java).apply {
            putExtra(ProfileActivity.EXTRA_USER_ID, userId)
        }
        context.startActivity(intent)
    }

    override fun navigateToPingPongJunior(context: Context, missionId: Int) {
        val intent = Intent(context, PingPongJuniorActivity::class.java).apply {
            putExtra(PingPongJuniorActivity.MISSION_ID, missionId)
        }
        context.startActivity(intent)
    }

    override fun navigateToNotificationCenter(context: Context) {
        val intent = Intent(context, NotificationCenterActivity::class.java)
        context.startActivity(intent)
    }

    override fun navigateToSuggestionDashboard(context: Context) {
        val intent = Intent(context, SuggestionDashboardActivity::class.java)
        context.startActivity(intent)
    }

    override fun navigateToSuggestionDetail(context: Context, suggestionId: Int) {
        val intent = Intent(context, SuggestionDetailActivity::class.java).apply {
            putExtra(SuggestionDetailActivity.SUGGESTION_ID, suggestionId)
        }
        context.startActivity(intent)
    }

    override fun openUrlInBrowser(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    }
}
