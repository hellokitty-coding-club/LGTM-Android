package com.lgtm.android.manage_mission.ping_pong_common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
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


    fun setData(role: Role, missionStatus: ProcessState, missionHistory: MissionProcessInfoUI) {
        refreshLayout()
        this.role = role
        this.missionStatus = missionStatus
        this.missionHistory = missionHistory
        binding.missionHistory = missionHistory
        setCurrentState()
    }

    private fun refreshLayout() {
        if (parent != null) {
            removeView(binding.root)
        }
        addView(binding.root)
    }


    private fun setCurrentState() {
        val missionStatus = requireNotNull(missionStatus)
        binding.apply {
            ivWaitingForPayment.setCurrentState(missionStatus)
            ivPaymentConfirm.setCurrentState(missionStatus)
            ivMissionProgress.setCurrentState(missionStatus)
            ivMissionCodeReview.setCurrentState(missionStatus)
            ivMissionReviewFinished.setCurrentState(missionStatus)
            ivPaymentConfirmLine.setCurrentState(missionStatus)
            ivMissionProgressLine.setCurrentState(missionStatus)
            ivMissionCodeReviewLine.setCurrentState(missionStatus)
            ivMissionReviewFinishedLine.setCurrentState(missionStatus)
        }
        if (missionStatus == ProcessState.MISSION_FINISHED && role == Role.REVIEWEE) {
            val string =
                getString(com.lgtm.android.common_ui.R.string.mission_review_finished) + "🎉"
            binding.tvMissionReviewFinished.text = string
        }
    }
}