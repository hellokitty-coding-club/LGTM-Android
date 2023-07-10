package com.lgtm.android.auth.ui.signup

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentTermsBinding
import com.lgtm.android.common_ui.base.BaseFragment


class TermsFragment : BaseFragment<FragmentTermsBinding>(R.layout.fragment_terms) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        setUpCheckBoxListeners()
        setUpTextViewListener()
        setNextButtonInitState()
    }

    private fun setUpTextViewListener() {
        binding.tvTermsService.setOnClickListener {
            openUrlInBrowser(TERMS_SERVICE_URL)
        }
        binding.tvTermsPrivacy.setOnClickListener {
            openUrlInBrowser(TERMS_PRIVACY_URL)
        }
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
        binding.cbTermsAll.setOnClickListener {
            val isChecked = binding.cbTermsAll.isChecked
            binding.cbTermsMarketing.isChecked = isChecked
            binding.cbTermsPrivacy.isChecked = isChecked
            binding.cbTermsService.isChecked = isChecked
            updateNextButtonState()
        }

        // 약관 및 정책
        binding.cbTermsService.setOnClickListener {
            updateTermsAllState()
            updateNextButtonState()
        }
        // 개인정보 처리 방침
        binding.cbTermsPrivacy.setOnClickListener {
            updateTermsAllState()
            updateNextButtonState()
        }
        // 이벤트
        binding.cbTermsMarketing.setOnClickListener {
            updateTermsAllState()
            updateNextButtonState()
        }
    }

    private fun setNextButtonInitState() {
        val isAgreeWithTerms =
            binding.cbTermsPrivacy.isChecked && binding.cbTermsService.isChecked
        signUpViewModel.setIsAgreeWithTerms(isAgreeWithTerms)
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

    companion object {
        // todo : url 수정
        // 약관 및 정책
        const val TERMS_SERVICE_URL = "https://www.naver.com"

        // 개인정보 처리 방침
        const val TERMS_PRIVACY_URL = "https://www.google.com"
    }
}
