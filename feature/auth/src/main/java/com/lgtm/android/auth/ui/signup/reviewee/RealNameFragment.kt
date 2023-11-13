package com.lgtm.android.auth.ui.signup.reviewee

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentRealNameBinding
import com.lgtm.android.auth.ui.signup.SignUpViewModel
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.common_ui.util.NetworkState
import com.lgtm.android.common_ui.util.setOnThrottleClickListener
import com.lgtm.domain.logging.SwmCommonLoggingScheme


class RealNameFragment : BaseFragment<FragmentRealNameBinding>(R.layout.fragment_real_name) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()
    override fun initializeViewModel() {
        viewModel = signUpViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        setupEditText()
        onRealNameChanged()
        setupCompleteButtonListener()
        observeSignUpStatus()
        shotRealNameExposureLogging()
    }

    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }

    private fun setupEditText() {
        binding.etRealname.apply {
            setLifecycleOwner(viewLifecycleOwner)
            bindEditTextData(signUpViewModel.realNameEditTextData)
        }
    }

    private fun onRealNameChanged() {
        signUpViewModel.realName.observe(viewLifecycleOwner) {
            signUpViewModel.fetchRealNameInfoStatus()
            signUpViewModel.setIsRealNameValid()
        }
    }

    private fun setupCompleteButtonListener() {
        binding.btnComplete.setOnThrottleClickListener {
            signUpViewModel.signUpJunior()
        }
    }

    private fun observeSignUpStatus() {
        signUpViewModel.signUpState.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkState.Init -> {
                    /* no-op */
                }

                is NetworkState.Success -> {
                    navigateToHomeActivity()
                    requireActivity().finish()
                }

                is NetworkState.Failure -> {
                    Toast.makeText(requireContext(), it.msg, Toast.LENGTH_SHORT).show()
                    signUpViewModel.clearSignUpState()
                }
            }
        }
    }

    private fun navigateToHomeActivity() {
        lgtmNavigator.navigateToMain(requireContext())
    }

    private fun shotRealNameExposureLogging() {
        val scheme = SwmCommonLoggingScheme.Builder()
            .setEventLogName("realNameExposure")
            .setScreenName(this.javaClass)
            .setLogData(mapOf("signUpStep" to 7, "juniorStep" to 2))
            .build()
        signUpViewModel.shotSwmLogging(scheme)
    }

}