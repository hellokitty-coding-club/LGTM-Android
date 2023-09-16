package com.lgtm.android.common_ui.model

import com.lgtm.domain.constants.ProcessState

data class MemberMissionStatusUI(
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