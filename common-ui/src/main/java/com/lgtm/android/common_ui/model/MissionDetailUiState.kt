package com.lgtm.android.common_ui.model

import com.lgtm.android.common_ui.constant.MissionStatusUI
import com.lgtm.domain.entity.response.MemberProfileVO
import com.lgtm.domain.entity.response.TechTagVO

data class MissionDetailUiState(
    val currentPeopleNumber: Int,
    val description: String,
    val maxPeopleNumber: Int,
    val memberProfile: MemberProfileVO,
    val memberType: String,
    val missionId: Int,
    val missionRepositoryUrl: String,
    val missionStatus: MissionStatusUI,
    val missionTitle: String,
    val notRecommendTo: String?,
    val price: Int,
    val recommendTo: String?,
    val remainingRegisterDays: Int,
    val scraped: Boolean,
    val techTagList: List<TechTagVO>
)