package com.lgtm.android.common_ui.model

import com.lgtm.android.common_ui.constant.MissionDetailButtonStatus
import com.lgtm.android.common_ui.constant.MissionStatusUI
import com.lgtm.domain.constants.Role
import com.lgtm.domain.entity.response.TechTagVO

data class MissionDetailUI(
    val currentPeopleNumber: Int,
    val description: String,
    val maxPeopleNumber: Int,
    val memberProfile: ProfileGlanceUI,
    val memberType: Role,
    val missionId: Int,
    val missionRepositoryUrl: String,
    val missionStatus: MissionStatusUI,
    val missionTitle: String,
    val notRecommendTo: String?,
    val price: Int,
    val recommendTo: String?,
    val remainingRegisterDays: Int,
    val scraped: Boolean,
    val techTagList: List<TechTagVO>,
    val missionDetailButtonStatusUI: MissionDetailButtonStatus
)