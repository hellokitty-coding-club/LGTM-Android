package com.lgtm.android.auth.ui.signup.reviewer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentCompanyNameBinding
import com.lgtm.android.auth.ui.signup.SignUpViewModel
import com.lgtm.android.common_ui.base.BaseFragment

class CompanyNameFragment :
    BaseFragment<FragmentCompanyNameBinding>(R.layout.fragment_company_name) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        setupEditText()
        onCompanyNameChanged()
        setupNextButtonListener()
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
        binding.btnNext.setOnClickListener {
            navigateToPositionFragment()
        }
    }

    private fun navigateToPositionFragment() {
        // 직책 입력 화면으로 이동
    }
}