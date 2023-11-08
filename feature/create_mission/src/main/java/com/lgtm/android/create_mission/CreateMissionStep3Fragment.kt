package com.lgtm.android.create_mission

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.common_ui.util.setOnThrottleClickListener
import com.lgtm.android.create_mission.databinding.FragmentCreateMissionStep3Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateMissionStep3Fragment :
    BaseFragment<FragmentCreateMissionStep3Binding>(R.layout.fragment_create_mission_step3) {
    private val createMissionViewModel by activityViewModels<CreateMissionViewModel>()

    override fun initializeViewModel() {
        viewModel = createMissionViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        setupEditText()
        setupNextButtonClickListener()
        onMissionDescriptionChanged()
    }

    private fun setupViewModel() {
        binding.viewModel = createMissionViewModel
    }

    private fun setupEditText() {
        binding.etMissionDescription.apply {
            setLifecycleOwner(viewLifecycleOwner)
            bindEditTextData(createMissionViewModel.descriptionEditTextData)
        }

        binding.etRecommendGroup.apply {
            setLifecycleOwner(viewLifecycleOwner)
            bindEditTextData(createMissionViewModel.recommendGroupEditTextData)
        }
        binding.etNotRecommendGroup.apply {
            setLifecycleOwner(viewLifecycleOwner)
            bindEditTextData(createMissionViewModel.notRecommendGroupEditTextData)
        }
    }

    private fun onMissionDescriptionChanged() {
        createMissionViewModel.description.observe(viewLifecycleOwner) {
            createMissionViewModel.updateMissionDescriptionInfoStatus()
            createMissionViewModel.setIsStep3DataValid()
        }
    }

    private fun setupNextButtonClickListener() {
        binding.btnNext.setOnThrottleClickListener {
            (requireActivity() as? CreateMissionActivity)?.setNextPage()
        }
    }
}