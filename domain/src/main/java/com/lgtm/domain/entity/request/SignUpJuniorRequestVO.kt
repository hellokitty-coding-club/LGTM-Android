package com.lgtm.domain.entity.request

import com.google.gson.annotations.SerializedName


data class SignUpJuniorRequestVO(
    val githubId: String,
    val githubOauthId: Int,
    val nickname: String,
    val deviceToken: String? = null,
    val profileImageUrl: String,
    val introduction: String,
    val tagList: List<String>,
    val educationalHistory: String,
    val realName: String,
    @SerializedName("agreeWithEventInfo")
    val isAgreeWithEventInfo: Boolean
)