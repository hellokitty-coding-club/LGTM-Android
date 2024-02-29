package com.lgtm.domain.entity.response

import java.io.Serializable
import java.time.LocalDateTime

data class MissionProcessInfoVO(
    val waitingForPaymentDate: LocalDateTime?,
    val paymentConfirmationDate: LocalDateTime?,
    val missionProceedingDate: LocalDateTime?,
    val codeReviewDate: LocalDateTime?,
    val feedbackReviewedDate: LocalDateTime?,
    val missionFinishedDate: LocalDateTime?,
) : Serializable