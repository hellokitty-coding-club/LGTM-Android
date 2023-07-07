package com.lgtm.android.auth.ui.signup

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
        setUpListeners()
        setNextButtonInitState()
    }

    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }

    private fun setUpListeners() {
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
        binding.cbTermsAll.isChecked =
            binding.cbTermsMarketing.isChecked && binding.cbTermsPrivacy.isChecked && binding.cbTermsService.isChecked
    }

    private fun updateNextButtonState() {
        val isAgreeWithTerms = binding.cbTermsPrivacy.isChecked && binding.cbTermsService.isChecked
        signUpViewModel.setIsAgreeWithTerms(isAgreeWithTerms)
    }
}
