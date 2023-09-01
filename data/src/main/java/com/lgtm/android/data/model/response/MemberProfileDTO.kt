package com.lgtm.android.data.model.response

import com.lgtm.domain.entity.response.MemberProfileVO

data class MemberProfileDTO(
    val company: String?,
    val githubId: String?,
    val memberId: Int?,
    val nickName: String?,
    val profileImageUrl: String?
) {
    fun toVO(): MemberProfileVO {
        return MemberProfileVO(
            company = requireNotNull(company),
            githubId = requireNotNull(githubId),
            memberId = requireNotNull(memberId),
            nickName = requireNotNull(nickName),
            profileImageUrl = requireNotNull(profileImageUrl)
        )
    }
}