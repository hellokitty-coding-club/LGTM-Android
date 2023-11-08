package com.lgtm.domain.entity.response

data class SignUpResponseVO(
    val memberId: Int,
    val githubId: String,
    val accessToken: String,
    val refreshToken: String,
    val memberType: String
)
