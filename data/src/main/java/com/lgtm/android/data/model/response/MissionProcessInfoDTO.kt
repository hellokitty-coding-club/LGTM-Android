package com.lgtm.android.data.model.response

import com.google.gson.annotations.SerializedName
import com.lgtm.android.data.model.mapper.parseDate
import com.lgtm.domain.entity.response.MissionProcessInfoVO

data class MissionProcessInfoDTO(
    @SerializedName("CODE_REVIEW")
    val codeReviewDate: String?,
    @SerializedName("PAYMENT_CONFIRMATION")
    val paymentConfirmationDate: String?,
    @SerializedName("WAITING_FOR_PAYMENT")
    val waitingForPaymentDate: String?,
    @SerializedName("MISSION_PROCEEDING")
    val missionProceedingDate: String?,
    @SerializedName("MISSION_FINISHED")
    val missionFinishedDate: String?,
    @SerializedName("FEEDBACK_REVIEWED")
    val feedbackReviewedDate: String?
) {
    fun toVO(): MissionProcessInfoVO {
        return MissionProcessInfoVO(
            paymentConfirmationDate = parseDate(paymentConfirmationDate),
            codeReviewDate = parseDate(codeReviewDate),
            waitingForPaymentDate = parseDate(waitingForPaymentDate),
            missionProceedingDate = parseDate(missionProceedingDate),
            missionFinishedDate = parseDate(missionFinishedDate),
            feedbackReviewedDate = parseDate(feedbackReviewedDate)
        )
    }
}