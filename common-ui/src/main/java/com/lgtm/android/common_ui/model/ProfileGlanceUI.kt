package com.lgtm.android.common_ui.model

import androidx.annotation.StringRes
import com.lgtm.domain.profile.Profile
import com.lgtm.domain.profile.ProfileViewType


data class ProfileGlanceUI(
    val memberId: Int,
    val profileImage: String,
    val nickname: String,
    val githubId: String,
    @StringRes val detailInfoLabel: Int,
    val detailInfo: String,
    override val viewType: ProfileViewType = ProfileViewType.PROFILE_GLANCE
) : Profile