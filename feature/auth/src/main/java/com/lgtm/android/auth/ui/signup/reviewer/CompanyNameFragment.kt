package com.lgtm.android.auth.ui.signup.reviewer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentCompanyNameBinding
import com.lgtm.android.auth.ui.signup.SignUpViewModel
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.common_ui.util.setOnThrottleClickListener
import com.lgtm.domain.logging.SwmCommonLoggingScheme

class CompanyNameFragment :
    BaseFragment<FragmentCompanyNameBinding>(R.layout.fragment_company_name) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()
    override fun initializeViewModel() {
        viewModel = signUpViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        setupEditText()
        onCompanyNameChanged()
        setupNextButtonListener()
        shotCompanyNameExposureLogging()
    }

    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }

    private fun setupEditText() {
        binding.etCompany.apply {
            setLifecycleOwner(viewLifecycleOwner)
            bindEditTextData(signUpViewModel.companyNameEditTextData)
        }
    }

    private fun onCompanyNameChanged() {
        signUpViewModel.companyName.observe(viewLifecycleOwner) {
            signUpViewModel.fetchCompanyNameInfoStatus()
            signUpViewModel.setIsCompanyNameValid()
        }
    }


    private fun setupNextButtonListener() {
        binding.btnNext.setOnThrottleClickListener {
            navigateToPositionFragment()
        }
    }

    private fun navigateToPositionFragment() {
        findNavController().navigate(R.id.action_companyNameFragment_to_positionFragment)
    }

    private fun shotCompanyNameExposureLogging() {
        val scheme = SwmCommonLoggingScheme.Builder()
            .setEventLogName("signUpExposure")
            .setScreenName(this.javaClass)
            .setLogData(mapOf("signUpStep" to 6, "seniorStep" to 1))
            .build()
        signUpViewModel.shotSwmLogging(scheme)
    }
}