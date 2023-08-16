package com.lgtm.android.create_mission

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.create_mission.databinding.FragmentCreateMissionStep1Binding


class CreateMissionStep1Fragment :
    BaseFragment<FragmentCreateMissionStep1Binding>(R.layout.fragment_create_mission_step1) {
    private val createMissionViewModel by activityViewModels<CreateMissionViewModel>()

    override fun initializeViewModel() {
        viewModel = createMissionViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        setupEditText()
    }

    private fun setupViewModel() {
        binding.viewModel = createMissionViewModel
    }

    private fun setupEditText() {
        binding.etMissionTitle.apply {
            setLifecycleOwner(viewLifecycleOwner)
            bindEditTextData(createMissionViewModel.missionTitleEditTextData)
        }

        binding.etMissionRepoUrl.apply {
            setLifecycleOwner(viewLifecycleOwner)
            bindEditTextData(createMissionViewModel.missionRepoUrlEditTextData)
        }
    }
}