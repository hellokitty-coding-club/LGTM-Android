package com.lgtm.android.data.model.response

import com.lgtm.domain.constants.UNKNOWN
import com.lgtm.domain.entity.response.AccountInfoVO

data class AccountInfoDTO(
    val accountNumber: String?,
    val bankName: String?,
    val price: Int?,
    val sendTo: String?
){
    fun toVO() : AccountInfoVO {
        return AccountInfoVO(
            accountNumber = accountNumber ?: throw NullPointerException("accountNumber is null"),
            bankName = bankName ?: throw NullPointerException("bankName is null"),
            price = price ?: throw NullPointerException("price is null"),
            sendTo = sendTo ?: UNKNOWN //TODO api 수정후 없애기
        )
    }
}