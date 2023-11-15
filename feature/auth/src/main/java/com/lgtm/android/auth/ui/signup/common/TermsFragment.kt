package com.lgtm.android.auth.ui.signup.common

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentTermsBinding
import com.lgtm.android.auth.ui.signup.SignUpViewModel
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.common_ui.util.setOnThrottleClickListener
import com.lgtm.domain.logging.SwmCommonLoggingScheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TermsFragment : BaseFragment<FragmentTermsBinding>(R.layout.fragment_terms) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()
    override fun initializeViewModel() {
        viewModel = signUpViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        setUpCheckBoxListeners()
        setUpTextViewListener()
        setupNextButtonListener()
        shotTermsExposureLogging()
    }

    private fun setupNextButtonListener() {
        binding.btnNext.setOnThrottleClickListener {
            setEventInfoAgreeState()
            navigateToNicknameFragment()
        }
    }

    private fun setEventInfoAgreeState() {
        signUpViewModel.setIsAgreeWithEventInfo(binding.cbTermsMarketing.isChecked)
    }

    private fun navigateToNicknameFragment() {
        findNavController().navigate(R.id.action_termsFragment_to_nicknameFragment)
    }


    private fun setUpTextViewListener() {
        binding.tvTermsService.setOnThrottleClickListener {
            shotTermsServiceClickLogging()
            openUrlInBrowser(TERMS_SERVICE_URL)
        }
        binding.tvTermsPrivacy.setOnThrottleClickListener {
            shotTermsPrivacyClickLogging()
            openUrlInBrowser(TERMS_PRIVACY_URL)
        }
    }

    private fun shotTermsServiceClickLogging() {
        val scheme = SwmCommonLoggingScheme.Builder()
            .setEventLogName("termsServiceClick")
            .setScreenName(this.javaClass)
            .build()
        signUpViewModel.shotSwmLogging(scheme)
    }

    private fun shotTermsPrivacyClickLogging() {
        val scheme = SwmCommonLoggingScheme.Builder()
            .setEventLogName("termsPrivacyClick")
            .setScreenName(this.javaClass)
            .build()
        signUpViewModel.shotSwmLogging(scheme)
    }

    private fun openUrlInBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }

    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }

    private fun setUpCheckBoxListeners() {
        binding.clTermsAll.setOnThrottleClickListener {
            toggleTermsAll()
            setDescendantsState(binding.cbTermsAll.isChecked)
            updateNextButtonState()
        }

        // 약관 및 정책
        binding.cbTermsService.setOnThrottleClickListener {
            updateTermsAllState()
            updateNextButtonState()
        }
        // 개인정보 처리 방침
        binding.cbTermsPrivacy.setOnThrottleClickListener {
            updateTermsAllState()
            updateNextButtonState()
        }
        // 이벤트
        binding.cbTermsMarketing.setOnThrottleClickListener {
            updateTermsAllState()
            updateNextButtonState()
        }
    }

    private fun toggleTermsAll() {
        binding.cbTermsAll.isChecked = !binding.cbTermsAll.isChecked
    }

    private fun setDescendantsState(isChecked: Boolean) {
        binding.cbTermsMarketing.isChecked = isChecked
        binding.cbTermsPrivacy.isChecked = isChecked
        binding.cbTermsService.isChecked = isChecked
    }

    private fun updateTermsAllState() {
        binding.cbTermsAll.isChecked = binding.cbTermsMarketing.isChecked &&
                binding.cbTermsPrivacy.isChecked &&
                binding.cbTermsService.isChecked
    }

    private fun updateNextButtonState() {
        val isAgreeWithTerms = binding.cbTermsPrivacy.isChecked && binding.cbTermsService.isChecked
        signUpViewModel.setIsAgreeWithTerms(isAgreeWithTerms)
    }

    private fun shotTermsExposureLogging() {
        val scheme = SwmCommonLoggingScheme.Builder()
            .setEventLogName("signUpExposure")
            .setScreenName(this.javaClass)
            .setLogData(mapOf("signUpStep" to 1))
            .build()
        signUpViewModel.shotSwmLogging(scheme)
    }

    companion object {
        // 약관 및 정책
        const val TERMS_SERVICE_URL =
            "https://www.notion.so/team-hkcc/c4f56b4e6e1b46e89c494e5b3919ed8c?pvs=4"

        // 개인정보 처리 방침
        const val TERMS_PRIVACY_URL =
            "https://www.notion.so/team-hkcc/31a6b7a98d1f4d148bb05cc826d0c9aa?pvs=4"
    }
}
