package com.lgtm.android.common_ui.navigator

import android.content.Context

interface FakeLgtmNavigator {
    fun navigateToSignIn(context: Context)

    fun navigateToMain(context: Context)

    fun navigateToCreateMission(context: Context)
}