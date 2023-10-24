package com.lgtm.domain.entity.response

data class DashboardVO(
    val memberInfoList: List<MemberMissionStatusVO>,
    val missionName: String,
    val techTagList: List<TechTagVO>
)