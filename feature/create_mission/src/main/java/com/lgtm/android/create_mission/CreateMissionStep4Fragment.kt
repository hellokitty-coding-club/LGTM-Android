package com.lgtm.android.create_mission

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.create_mission.databinding.FragmentCreateMissionStep4Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateMissionStep4Fragment :
    BaseFragment<FragmentCreateMissionStep4Binding>(R.layout.fragment_create_mission_step4) {
    private val createMissionViewModel by activityViewModels<CreateMissionViewModel>()

    override fun initializeViewModel() {
        viewModel = createMissionViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        setupNextButtonClickListener()
    }

    private fun setupViewModel() {
        binding.viewModel = createMissionViewModel
    }


    private fun setupNextButtonClickListener() {
        binding.btnNext.setOnClickListener {
            (requireActivity() as? CreateMissionActivity)?.onNextButtonClick(this.javaClass)
        }
    }
}