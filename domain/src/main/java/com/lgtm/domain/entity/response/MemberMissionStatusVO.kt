package com.lgtm.domain.entity.response

import com.lgtm.domain.constants.ProcessState

data class MemberMissionStatusVO(
    val githubId: String,
    val githubPrUrl: String,
    val memberId: Int,
    val nickname: String,
    val missionFinishedDate: String,
    val paymentDate: String,
    val processStatus: ProcessState,
    val profileImageUrl: String,
    val isMissionSubmitted : Boolean
)