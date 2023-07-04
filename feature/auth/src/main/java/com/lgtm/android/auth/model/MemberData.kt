package com.lgtm.android.auth.model

data class MemberData(
    val memberId: Int,
    val githubId: String,
    val accessToken: String,
    val refreshToken: String,
    val registered: Boolean
)
