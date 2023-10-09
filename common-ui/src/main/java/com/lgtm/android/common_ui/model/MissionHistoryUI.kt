package com.lgtm.android.common_ui.model

import android.text.SpannableString
import java.io.Serializable

data class MissionHistoryUI(
    val waitingForPaymentDate: String?,
    val waitingForPaymentDetail: SpannableString? = null,
    val paymentConfirmationDate: String?,
    val paymentConfirmationDetail: SpannableString? = null,
    val missionProceedingDate: String?,
    val missionProceedingDetail: SpannableString? = null,
    val codeReviewDate: String?,
    val codeReviewDetail: SpannableString? = null,
    val missionFinishedDate: String?,
    val feedbackReviewedDate: String?
) : Serializable