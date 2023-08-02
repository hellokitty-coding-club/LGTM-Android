package com.lgtm.android.auth.ui.signup.reviewee

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentRealNameBinding
import com.lgtm.android.auth.ui.signup.SignUpViewModel
import com.lgtm.android.common_ui.base.BaseFragment


class RealNameFragment : BaseFragment<FragmentRealNameBinding>(R.layout.fragment_real_name) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        setupEditText()
        onRealNameChanged()
        setupCompleteButtonListener()
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
        binding.btnComplete.setOnClickListener {
            signUpViewModel.signUpJunior()
            navigateToHomeActivity()
        }
    }

    private fun navigateToHomeActivity() {
//        findNavController().navigate(R.id.action_nicknameFragment_to_techTagFragment)
    }


}