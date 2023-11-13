package com.lgtm.android.auth.ui.signup.reviewer

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentCareerPeriodBinding
import com.lgtm.android.auth.ui.signup.SignUpViewModel
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.common_ui.util.setOnThrottleClickListener
import com.lgtm.domain.logging.SwmCommonLoggingScheme


class CareerPeriodFragment :
    BaseFragment<FragmentCareerPeriodBinding>(R.layout.fragment_career_period) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()
    override fun initializeViewModel() {
        viewModel = signUpViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        onCareerPeriodChanged()
        setupNextButtonListener()
        shotCareerPeriodExposureLogging()
    }


    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }

    private fun onCareerPeriodChanged() {
        binding.etCareerPeriod.addTextChangedListener {
            signUpViewModel.setCareerPeriod(it.toString())
            signUpViewModel.fetchCareerPeriodInfoStatus()
            signUpViewModel.setIsCareerPeriodValid()
        }
    }


    private fun setupNextButtonListener() {
        binding.btnNext.setOnThrottleClickListener {
            navigateToBankAccountFragment()
        }
    }

    private fun navigateToBankAccountFragment() {
        findNavController().navigate(R.id.action_careerPeriodFragment_to_bankAccountFragment)
    }

    private fun shotCareerPeriodExposureLogging() {
        val scheme = SwmCommonLoggingScheme.Builder()
            .setEventLogName("careerExposure")
            .setScreenName(this.javaClass)
            .setLogData(mapOf("signUpStep" to 8, "seniorStep" to 3))
            .build()
        viewModel.shotSwmLogging(scheme)
    }

}