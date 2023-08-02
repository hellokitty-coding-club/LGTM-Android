package com.lgtm.domain.entity.request

data class SignUpSeniorRequestVO(
    val githubId: String,
    val githubOauthId: Int,
    val nickName: String,
    val deviceToken: String?,
    val profileImageUrl: String,
    val introduction: String,
    val isAgreeWithEventInfo: Boolean,
    val tagList: List<String>,
    val companyInfo: String,
    val careerPeriod: Int,
    val position: String,
    val accountNumber: String,
    val bankName: String
)