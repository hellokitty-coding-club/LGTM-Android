package com.lgtm.domain.entity.response

data class MemberProfileVO(
    val company: String,
    val githubId: String,
    val memberId: Int,
    val nickName: String,
    val position : String,
    val profileImageUrl: String
)