package com.lgtm.domain.entity.response

import com.lgtm.domain.constants.ProcessState
import java.time.LocalDateTime

data class MemberMissionStatusVO(
    val githubId: String,
    val githubPrUrl: String,
    val memberId: Int,
    val nickname: String,
    val missionFinishedDate: LocalDateTime?,
    val paymentDate: LocalDateTime?,
    val processStatus: ProcessState,
    val profileImageUrl: String,
    val isMissionSubmitted: Boolean,
)