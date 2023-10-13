package com.lgtm.android.manage_mission.ping_pong_junior

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.manage_mission.R
import com.lgtm.android.manage_mission.databinding.FragmentSubmittingMissionBinding


class SubmittingMissionFragment :
    BaseFragment<FragmentSubmittingMissionBinding>(R.layout.fragment_submitting_mission) {
    private val pingPongJuniorViewModel by activityViewModels<PingPongJuniorViewModel>()

    override fun initializeViewModel() {
        viewModel = pingPongJuniorViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpViewModel()
        setEditText()
        onGithubPrUrlChanged()
    }

    private fun setUpViewModel() {
        binding.viewModel = pingPongJuniorViewModel
    }

    private fun setEditText() {
        binding.etGithubUrl.apply {
            setLifecycleOwner(viewLifecycleOwner)
            bindEditTextData(pingPongJuniorViewModel.pullRequestUrlEditTextData)
            setMaxLine(3)
        }
    }

    private fun onGithubPrUrlChanged() {
        pingPongJuniorViewModel.pullRequestUrl.observe(viewLifecycleOwner) {
            pingPongJuniorViewModel.updateMissionTitleInfoStatus()
            pingPongJuniorViewModel.setIsValidUrl()
        }
    }
}