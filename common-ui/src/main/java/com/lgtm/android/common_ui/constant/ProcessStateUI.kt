package com.lgtm.android.common_ui.constant

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.lgtm.android.common_ui.R
import com.lgtm.domain.constants.ProcessState

enum class ProcessStateUI(
    @DrawableRes val tagImg: Int,
    @StringRes val tagText: Int,
    @StringRes val detailMessage: Int
) {

    WAITING_FOR_PAYMENT(
        R.drawable.ic_reviewee_green,
        R.string.waiting_for_payment,
        R.string.waiting_for_payment_message
    ),
    PAYMENT_CONFIRMATION(
        R.drawable.ic_reviewer_green,
        R.string.payment_confirmation,
        R.string.payment_confirmation_message
    ),
    MISSION_PROCEEDING(
        R.drawable.ic_reviewee_green,
        R.string.mission_progress,
        R.string.mission_progress_message
    ),
    CODE_REVIEW(R.drawable.ic_reviewer_green, R.string.code_review, R.string.code_review_message),
    MISSION_FINISHED(
        R.drawable.ic_reviewee_green,
        R.string.mission_review_finished,
        R.string.mission_review_finished_message
    );

    companion object {
        fun getProcessStateUI(processState: ProcessState): ProcessStateUI {
            values().forEach {
                if (it.name == processState.name) return it
            }
            throw IllegalStateException("ProcessState not found")
        }
    }
}