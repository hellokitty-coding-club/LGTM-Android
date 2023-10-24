package com.lgtm.android.common_ui.model

import com.lgtm.domain.constants.ProcessState

data class PingPongSeniorUI(
    val buttonTitle: String,
    val feedbackId: Int?,
    val githubId: String,
    val memberId: Int,
    val missionProcessInfo: MissionProcessInfoUI,
    val nickname: String,
    val processState: ProcessState
)