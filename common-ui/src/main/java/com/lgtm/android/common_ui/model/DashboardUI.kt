package com.lgtm.android.common_ui.model

import com.lgtm.domain.entity.response.TechTagVO

data class DashboardUI(
    val memberInfoList: List<MemberMissionStatusUI>,
    val missionName: String,
    val techTagList: List<TechTagVO>
)
