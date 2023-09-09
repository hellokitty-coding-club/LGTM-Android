package com.lgtm.android.common_ui.model

import androidx.annotation.StringRes


data class ProfileGlanceUI(
    val memberId: Int,
    val profileImage: String,
    val nickname: String,
    val githubId: String,
    @StringRes val detailInfoLabel: Int,
    val detailInfo: String,
)