package com.lgtm.android.create_mission

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.common_ui.util.TechTagChipGroup
import com.lgtm.android.common_ui.util.TechTagTheme
import com.lgtm.android.create_mission.databinding.FragmentCreateMissionStep2Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateMissionStep2Fragment :
    BaseFragment<FragmentCreateMissionStep2Binding>(R.layout.fragment_create_mission_step2) {
    private val createMissionViewModel by activityViewModels<CreateMissionViewModel>()

    override fun initializeViewModel() {
        viewModel = createMissionViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        setChips()
        observeTechTagList()
        setupNextButtonClickListener()
    }

    private fun setupViewModel() {
        binding.viewModel = createMissionViewModel
    }

    private fun setChips() {
        createMissionViewModel.techTagList.let {
            TechTagChipGroup(binding.chipTechTag, TechTagTheme.LIGHT).setChipGroup(it)
        }
    }

    private fun observeTechTagList() {
        createMissionViewModel.techTagList.observe(viewLifecycleOwner) {
            createMissionViewModel.setIsTechTagValid()
        }
    }


    private fun setupNextButtonClickListener() {
        binding.btnNext.setOnClickListener {
            (requireActivity() as? CreateMissionActivity)?.onNextButtonClick(this.javaClass)
        }
    }
}