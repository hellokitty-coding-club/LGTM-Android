package com.lgtm.android.common_ui.navigator

import android.content.Context

interface FakeLgtmNavigator {
    fun navigateToSignIn(context: Context)

    fun navigateToMain(context: Context)

    fun navigateToCreateMission(context: Context)

    fun navigateToMissionDetail(context: Context, missionId: Int)

    fun navigateToDashboard(context: Context, missionId: Int)

    fun navigateToProfile(context: Context, userId: Int? = null)

    fun navigateToPingPongJunior(context: Context, missionId: Int)

    fun navigateToNotificationCenter(context: Context)

    fun navigateToSuggestionDashboard(context: Context)

    fun navigateToSuggestionDetail(context: Context, suggestionId: Int)
}