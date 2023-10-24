package com.lgtm.domain.entity.response

import com.lgtm.domain.constants.ProcessState

data class PingPongSeniorVO(
    val buttonTitle: String,
    val feedbackId: Int?,
    val githubId: String,
    val memberId: Int,
    val missionProcessInfo: MissionProcessInfoVO,
    val nickname: String,
    val processState: ProcessState,
    val depositorName: String?,
)