package com.lgtm.android.create_mission

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.common_ui.util.setOnThrottleClickListener
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
        onPriceChanged()
        onRecruitNumberChanged()
    }

    private fun onRecruitNumberChanged() {
        binding.etNumOfRecruits.addTextChangedListener {
            createMissionViewModel.setNumOfRecruits(it.toString())
            createMissionViewModel.setIsStep4DataValid()
        }
    }

    private fun onPriceChanged() {
        binding.etPrice.addTextChangedListener {
            createMissionViewModel.setPrice(it.toString())
            createMissionViewModel.setIsStep4DataValid()
        }
    }

    private fun setupViewModel() {
        binding.viewModel = createMissionViewModel
    }


    private fun setupNextButtonClickListener() {
        binding.btnNext.setOnThrottleClickListener {
            (requireActivity() as? CreateMissionActivity)?.setNextPage()
        }
    }
}