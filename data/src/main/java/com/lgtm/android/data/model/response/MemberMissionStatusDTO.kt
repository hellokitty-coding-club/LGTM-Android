package com.lgtm.android.data.model.response

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
            missionFinishedDate = missionFinishedDate ?: UNKNOWN,
            nickname = nickname ?: UNKNOWN,
            paymentDate = paymentDate ?: UNKNOWN,
            processStatus = processStatus ?: UNKNOWN,
            profileImageUrl = profileImageUrl ?: UNKNOWN
        )
    }
}