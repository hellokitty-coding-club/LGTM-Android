package com.lgtm.domain.entity.request


data class SignUpJuniorRequestVO(
    val githubId: String,
    val githubOauthId: Int,
    val nickName: String,
    val deviceToken: String?,
    val profileImageUrl: String,
    val introduction: String,
    val tagList: List<String>,
    val educationalHistory: String,
    val realName: String,
    val isAgreeWithEventInfo: Boolean
)