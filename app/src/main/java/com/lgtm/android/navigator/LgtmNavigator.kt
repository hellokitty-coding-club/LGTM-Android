package com.lgtm.android.navigator

import android.content.Context
import android.content.Intent
import com.lgtm.android.auth.ui.SignInActivity
import com.lgtm.android.common_ui.navigator.FakeLgtmNavigator
import com.lgtm.android.create_mission.CreateMissionActivity
import com.lgtm.android.main.MainActivity
import javax.inject.Inject

class LgtmNavigator @Inject constructor(
) : FakeLgtmNavigator {
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
}
