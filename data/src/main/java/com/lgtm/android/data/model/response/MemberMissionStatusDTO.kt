package com.lgtm.android.data.model.response

import com.lgtm.domain.constants.ProcessState
import com.lgtm.domain.constants.ProcessState.Companion.getProcessState
import com.lgtm.domain.constants.UNKNOWN
import com.lgtm.domain.entity.response.MemberMissionStatusVO

data class MemberMissionStatusDTO(
    val githubId: String?,
    val githubPrUrl: String?,
    val memberId: Int?,
    val missionFinishedDate: String?,
    val nickname: String?,
    val paymentDate: String?,
    val processStatus: String?,
    val profileImageUrl: String?
) {
    fun toVO(): MemberMissionStatusVO {
        return MemberMissionStatusVO(
            githubId = githubId ?: UNKNOWN,
            githubPrUrl = githubPrUrl ?: UNKNOWN,
            memberId = requireNotNull(memberId),
            nickname = nickname ?: UNKNOWN,
            missionFinishedDate = missionFinishedDate ?: UNKNOWN,
            paymentDate = paymentDate ?: UNKNOWN,
            processStatus = getProcessState(processStatus),
            profileImageUrl = profileImageUrl ?: UNKNOWN,
            isMissionSubmitted = ProcessState.isMissionSubmitted(processStatus)
        )
    }
}