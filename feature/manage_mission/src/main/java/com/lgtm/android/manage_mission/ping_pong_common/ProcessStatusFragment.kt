package com.lgtm.android.manage_mission.ping_pong_common

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.manage_mission.R
import com.lgtm.android.manage_mission.databinding.FragmentProcessStatusBinding
import com.lgtm.android.manage_mission.ping_pong_junior.PingPongJuniorViewModel
import com.lgtm.domain.constants.ProcessState
import com.lgtm.domain.constants.Role
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class ProcessStatusFragment :
    BaseFragment<FragmentProcessStatusBinding>(R.layout.fragment_process_status) {

    private val missionStatusViewModel by activityViewModels<PingPongJuniorViewModel>()
    private var role: Role? = null
    private var missionStatus: ProcessState? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            role = it.getSerializable(ROLE) as Role
            missionStatus = it.getSerializable(MISSION_STATUS) as ProcessState
        }
        Log.d(TAG, "onCreate: $role , $missionStatus")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        }
    }

    override fun initializeViewModel() {
        viewModel = missionStatusViewModel
    }

    companion object {
        @JvmStatic
        fun newInstance(role: Role, missionStatus: ProcessState) =
            ProcessStatusFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ROLE, role)
                    putSerializable(MISSION_STATUS, missionStatus)
                }
            }
    }
}


private const val ROLE = "ROLE"
private const val MISSION_STATUS = "MISSION_STATUS"
