package com.lgtm.android.data.model.request

data class SignUpSeniorRequestDTO(
    val accountNumber: String,
    val agreeWithEventInfo: Boolean,
    val bankName: String,
    val careerPeriod: Int,
    val companyInfo: String,
    val deviceToken: String,
    val githubId: String,
    val githubOauthId: Int,
    val introduction: String,
    val nickName: String,
    val position: String,
    val profileImageUrl: String,
    val tagList: List<String>
)