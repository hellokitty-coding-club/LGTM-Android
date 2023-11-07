package com.lgtm.android.manage_mission.ping_pong_junior

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lgtm.android.common_ui.R.string
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.common_ui.util.setOnThrottleClickListener
import com.lgtm.android.manage_mission.R
import com.lgtm.android.manage_mission.databinding.FragmentSubmittedMissionBinding
import com.lgtm.domain.constants.ProcessState
import com.lgtm.domain.constants.UNKNOWN


class SubmittedMissionFragment :
    BaseFragment<FragmentSubmittedMissionBinding>(R.layout.fragment_submitted_mission) {
    private val pingPongJuniorViewModel by activityViewModels<PingPongJuniorViewModel>()

    override fun initializeViewModel() {
        viewModel = pingPongJuniorViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpViewModel()
        onClickSubmittedMission()
        observeMissionStatusInfo()
    }

    private fun observeMissionStatusInfo() {
        pingPongJuniorViewModel.pingPongJuniorUI.observe(viewLifecycleOwner) {
            setTitle()
        }
    }

    private fun setTitle() {
        val status = pingPongJuniorViewModel.pingPongJuniorUI.value?.processStatus
        binding.tvSubmittedMission.text =
            when (status) {
                ProcessState.CODE_REVIEW -> getString(string.submitted_mission)
                ProcessState.MISSION_FINISHED -> getString(string.check_out_mission_review)
                else -> UNKNOWN
            }
    }

    private fun onClickSubmittedMission() {
        binding.clSubmittedMission.setOnThrottleClickListener {
            val url = pingPongJuniorViewModel.submittedPrUrl.value
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    private fun setUpViewModel() {
        binding.viewModel = pingPongJuniorViewModel
    }

}