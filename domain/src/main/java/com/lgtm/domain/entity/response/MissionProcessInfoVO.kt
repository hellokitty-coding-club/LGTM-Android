package com.lgtm.domain.entity.response

import java.io.Serializable

data class MissionProcessInfoVO(
    val waitingForPaymentDate: String?,
    val paymentConfirmationDate: String?,
    val missionProceedingDate: String?,
    val codeReviewDate: String?,
    val feedbackReviewedDate: String?,
    val missionFinishedDate: String?
) : Serializable