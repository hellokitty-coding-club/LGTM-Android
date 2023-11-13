package com.lgtm.android.auth.ui.signup.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentTechTagBinding
import com.lgtm.android.auth.ui.signup.SignUpViewModel
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.common_ui.util.TechTagChipGroup
import com.lgtm.android.common_ui.util.TechTagTheme
import com.lgtm.android.common_ui.util.setOnThrottleClickListener
import com.lgtm.domain.logging.SwmCommonLoggingScheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TechTagFragment : BaseFragment<FragmentTechTagBinding>(R.layout.fragment_tech_tag) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()
    override fun initializeViewModel() {
        viewModel = signUpViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        setChips()
        observeTechTagList()
        setupNextButtonListener()
        shotTechTagExposureLogging()
    }

    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }

    private fun setChips() {
        signUpViewModel.techTagList.let {
            TechTagChipGroup(binding.chipTechTag, TechTagTheme.DARK).setChipGroup(it)
        }
    }

    private fun observeTechTagList() {
        signUpViewModel.techTagList.observe(viewLifecycleOwner) {
            signUpViewModel.setIsTechTagValid()
        }
    }


    private fun setupNextButtonListener() {
        binding.btnNext.setOnThrottleClickListener {
            navigateToIntroductionFragment()
        }
    }

    private fun navigateToIntroductionFragment() {
        findNavController().navigate(R.id.action_techTagFragment_to_introductionFragment)
    }

    private fun shotTechTagExposureLogging() {
        val scheme = SwmCommonLoggingScheme.Builder()
            .setEventLogName("techTagExposure")
            .setScreenName(this.javaClass)
            .setLogData(mapOf("signUpStep" to 3))
            .build()
        signUpViewModel.shotSwmLogging(scheme)
    }
}