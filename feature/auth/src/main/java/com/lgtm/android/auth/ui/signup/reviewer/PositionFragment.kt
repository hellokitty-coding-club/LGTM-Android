package com.lgtm.android.auth.ui.signup.reviewer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentPositionBinding
import com.lgtm.android.auth.ui.signup.SignUpViewModel
import com.lgtm.android.common_ui.base.BaseFragment

class PositionFragment :
    BaseFragment<FragmentPositionBinding>(R.layout.fragment_position) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        setupEditText()
        onPositionNameChanged()
        setupNextButtonListener()
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
        binding.btnNext.setOnClickListener {
            navigateToPositionFragment()
        }
    }

    private fun navigateToPositionFragment() {
        // 직책 입력 화면으로 이동
    }
}