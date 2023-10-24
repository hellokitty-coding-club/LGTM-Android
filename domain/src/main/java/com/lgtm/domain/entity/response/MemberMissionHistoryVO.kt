package com.lgtm.domain.entity.response

data class MemberMissionHistoryVO(
    val missionId: Int,
    val missionTitle: String,
    val techTagList: List<TechTagVO>
)