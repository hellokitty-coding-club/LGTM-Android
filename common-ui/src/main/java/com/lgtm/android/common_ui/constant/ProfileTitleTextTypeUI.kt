package com.lgtm.android.common_ui.constant

import androidx.annotation.StringRes
import com.lgtm.android.common_ui.R
import com.lgtm.domain.profile.ProfileTitleTextType

enum class ProfileTitleTextTypeUI(@StringRes val stringRes: Int, val viewType: ProfileTitleTextType) {
    STACK(R.string.using_stack, ProfileTitleTextType.STACK),
    INTRODUCTION(R.string.simple_introduction, ProfileTitleTextType.INTRODUCTION),
    CAREER(R.string.career, ProfileTitleTextType.CAREER),
    CONDUCTED_MISSION(R.string.conducted_mission, ProfileTitleTextType.CONDUCTED_MISSION),
    PARTICIPATED_MISSION(R.string.participated_mission, ProfileTitleTextType.PARTICIPATED_MISSION);

    companion object {
        fun getStringRes(type: ProfileTitleTextType): ProfileTitleTextTypeUI =
            values().first { it.viewType == type }
    }
}