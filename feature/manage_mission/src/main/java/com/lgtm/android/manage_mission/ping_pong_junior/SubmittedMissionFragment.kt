package com.lgtm.android.manage_mission.ping_pong_junior

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.manage_mission.R
import com.lgtm.android.manage_mission.databinding.FragmentSubmittedMissionBinding


class SubmittedMissionFragment :
    BaseFragment<FragmentSubmittedMissionBinding>(R.layout.fragment_submitted_mission) {
    private val pingPongJuniorViewModel by activityViewModels<PingPongJuniorViewModel>()

    override fun initializeViewModel() {
        viewModel = pingPongJuniorViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpViewModel()
        onClickSubmittedMission()
    }

    private fun onClickSubmittedMission() {
        binding.clSubmittedMission.setOnClickListener {
            val url = pingPongJuniorViewModel.submittedPrUrl.value
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    private fun setUpViewModel() {
        binding.viewModel = pingPongJuniorViewModel
    }

}