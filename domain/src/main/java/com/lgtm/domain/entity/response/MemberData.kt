package com.lgtm.domain.entity.response

data class MemberData(
    val memberId: Int?,
    val githubId: String?,
    val githubOauthId: Int?,
    val accessToken: String?,
    val refreshToken: String?,
    val profileImageUrl: String?,
    val memberType: String?,
    val registered: Boolean?
)
