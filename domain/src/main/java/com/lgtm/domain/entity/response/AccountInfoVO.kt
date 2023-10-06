package com.lgtm.domain.entity.response

data class AccountInfoVO(
    val accountNumber: String,
    val bankName: String,
    val price: Int,
    val sendTo: String
)