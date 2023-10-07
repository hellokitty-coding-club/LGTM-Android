package com.lgtm.domain.entity.response

data class MissionHistoryVO(
    val paymentConfirmationDate: String?,
    val codeReviewDate: String?,
    val waitingForPaymentDate: String?,
    val missionProceedingDate: String?
)