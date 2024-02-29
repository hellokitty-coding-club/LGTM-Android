package com.lgtm.android.common_ui.model

import android.text.SpannableString
import java.io.Serializable

data class MissionProcessInfoUI(
    val waitingForPaymentDate: SpannableString?,
    val waitingForPaymentDetail: SpannableString? = null,
    val paymentConfirmationDate: SpannableString?,
    val paymentConfirmationDetail: SpannableString? = null,
    val missionProceedingDate: SpannableString?,
    val missionProceedingDetail: SpannableString? = null,
    val codeReviewDate: SpannableString?,
    val codeReviewDetail: SpannableString? = null,
    val missionFinishedDate: SpannableString?,
    val feedbackReviewedDate: SpannableString?,
    val depositorName: String? = null,
) : Serializable