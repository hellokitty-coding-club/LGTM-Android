package com.lgtm.android.manage_mission.ping_pong_common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.lgtm.android.manage_mission.databinding.LayoutProcessStatusBinding
import com.lgtm.domain.constants.ProcessState
import com.lgtm.domain.constants.Role
import com.lgtm.domain.entity.response.MissionHistoryVO


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
    private var missionHistory: MissionHistoryVO? = null

    init {
        addView(binding.root)
    }

    fun setData(role: Role, missionStatus: ProcessState, missionHistory: MissionHistoryVO) {
        this.role = role
        this.missionStatus = missionStatus
        this.missionHistory = missionHistory
        binding.missionHistory = missionHistory
        setCurrentState()
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
    }
}


private const val ROLE = "ROLE"
private const val MISSION_STATUS = "MISSION_STATUS"
private const val MISSION_HISTORY = "MISSION_HISTORY"
