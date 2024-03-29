package com.lgtm.android.auth.ui.signup.reviewer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentPositionBinding
import com.lgtm.android.auth.ui.signup.SignUpViewModel
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.common_ui.util.setOnThrottleClickListener
import com.lgtm.domain.logging.SwmCommonLoggingScheme

class PositionFragment :
    BaseFragment<FragmentPositionBinding>(R.layout.fragment_position) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()
    override fun initializeViewModel() {
        viewModel = signUpViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        setupEditText()
        onPositionNameChanged()
        setupNextButtonListener()
        shotPositionExposureLogging()
    }

    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }

    private fun setupEditText() {
        binding.etPosition.apply {
            setLifecycleOwner(viewLifecycleOwner)
            bindEditTextData(signUpViewModel.positionEditTextData)
        }
    }

    private fun onPositionNameChanged() {
        signUpViewModel.position.observe(viewLifecycleOwner) {
            signUpViewModel.fetchPositionInfoStatus()
            signUpViewModel.setIsPositionValid()
        }
    }


    private fun setupNextButtonListener() {
        binding.btnNext.setOnThrottleClickListener {
            navigateToPositionFragment()
        }
    }

    private fun navigateToPositionFragment() {
        findNavController().navigate(R.id.action_positionFragment_to_careerPeriodFragment)
    }

    private fun shotPositionExposureLogging() {
        val scheme = SwmCommonLoggingScheme.Builder()
            .setEventLogName("signUpExposure")
            .setScreenName(this.javaClass)
            .setLogData(mapOf("signUpStep" to 7, "seniorStep" to 2))
            .build()
        signUpViewModel.shotSwmLogging(scheme)
    }
}