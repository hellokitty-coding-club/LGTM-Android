package com.lgtm.android.data.model.response

import com.google.gson.annotations.SerializedName
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
            paymentConfirmationDate = paymentConfirmationDate,
            codeReviewDate = codeReviewDate,
            waitingForPaymentDate = waitingForPaymentDate,
            missionProceedingDate = missionProceedingDate,
            missionFinishedDate = missionFinishedDate,
            feedbackReviewedDate = feedbackReviewedDate
        )
    }
}