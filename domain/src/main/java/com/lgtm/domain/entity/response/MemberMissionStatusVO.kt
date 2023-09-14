package com.lgtm.domain.entity.response

data class MemberMissionStatusVO(
    val githubId: String,
    val githubPrUrl: String,
    val memberId: Int,
    val missionFinishedDate: String,
    val nickname: String,
    val paymentDate: String,
    val processStatus: String,
    val profileImageUrl: String
)