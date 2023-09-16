package com.lgtm.android.common_ui.model

import com.lgtm.android.common_ui.constant.ProcessStateUI

data class MemberMissionStatusUI(
    val githubId: String,
    val githubPrUrl: String,
    val memberId: Int,
    val nickname: String,
    val missionFinishedDate: String,
    val paymentDate: String,
    val processStatus: ProcessStateUI,
    val profileImageUrl: String,
    val isMissionSubmitted : Boolean
)