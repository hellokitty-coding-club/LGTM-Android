package com.lgtm.android.manage_mission.ping_pong_common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.lgtm.android.common_ui.R.string
import com.lgtm.android.common_ui.R.style
import com.lgtm.android.common_ui.model.MissionProcessInfoUI
import com.lgtm.android.common_ui.util.getString
import com.lgtm.android.manage_mission.databinding.LayoutProcessStatusBinding
import com.lgtm.domain.constants.ProcessState
import com.lgtm.domain.constants.Role


class ProcessStatusLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutProcessStatusBinding by lazy {
        LayoutProcessStatusBinding.inflate(LayoutInflater.from(context), this, false)
    }

    private var role: Role? = null
    private var missionStatus: ProcessState? = null
    private var missionHistory: MissionProcessInfoUI? = null
    private val missionStatusCircle: List<PingPongCircle> = listOf(
        binding.ivWaitingForPayment, binding.ivPaymentConfirm, binding.ivMissionProgress,
        binding.ivMissionCodeReview, binding.ivMissionReviewFinished,
    )
    private val missionStatusLine: List<PingPongLine> = listOf(
        binding.ivPaymentConfirmLine, binding.ivMissionProgressLine,
        binding.ivMissionCodeReviewLine, binding.ivMissionReviewFinishedLine
    )
    private val bindingMap = mapOf(
        ProcessState.WAITING_FOR_PAYMENT to binding.tvWaitingForPayment,
        ProcessState.PAYMENT_CONFIRMATION to binding.tvPaymentConfirm,
        ProcessState.MISSION_PROCEEDING to binding.tvMissionProgress,
        ProcessState.CODE_REVIEW to binding.tvMissionCodeReview,
        ProcessState.MISSION_FINISHED to binding.tvMissionReviewFinished,
        ProcessState.FEEDBACK_REVIEWED to binding.tvMissionReviewFinished
    )

    fun setData(role: Role, missionStatus: ProcessState, missionHistory: MissionProcessInfoUI) {
        refreshLayout()
        this.role = role
        this.missionStatus = missionStatus
        this.missionHistory = missionHistory
        binding.missionHistory = missionHistory
        setCurrentState()
    }

    private fun refreshLayout() {
        if (parent != null)
            removeView(binding.root)
        addView(binding.root)
    }


    private fun setCurrentState() {
        val missionStatus = requireNotNull(missionStatus)
        setCurrentStateStateStyle()
        binding.apply {
            missionStatusCircle.forEach { it.setCurrentState(missionStatus) }
            missionStatusLine.forEach { it.setCurrentState(missionStatus) }
        }
        addCongratulationEmoji()
    }

    private fun setCurrentStateStateStyle() {
        bindingMap[missionStatus]?.setTextAppearance(style.Body1B)
    }

    private fun addCongratulationEmoji() {
        if (missionStatus == ProcessState.MISSION_FINISHED && role == Role.REVIEWEE) {
            val string = getString(string.mission_review_finished) + "🎉"
            binding.tvMissionReviewFinished.text = string
        }
    }

}