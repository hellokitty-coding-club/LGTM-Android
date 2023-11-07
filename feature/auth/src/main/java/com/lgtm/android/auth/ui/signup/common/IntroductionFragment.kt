package com.lgtm.android.auth.ui.signup.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentIntroductionBinding
import com.lgtm.android.auth.ui.signup.SignUpViewModel
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.common_ui.util.setOnThrottleClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroductionFragment :
    BaseFragment<FragmentIntroductionBinding>(R.layout.fragment_introduction) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()
    override fun initializeViewModel() {
        viewModel = signUpViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        setupEditText()
        onIntroductionChanged()
        setupNextButtonListener()
    }

    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }

    private fun setupEditText() {
        binding.etIntroduction.apply {
            setLifecycleOwner(viewLifecycleOwner)
            bindEditTextData(signUpViewModel.introEditTextData)
        }
    }

    private fun onIntroductionChanged() {
        signUpViewModel.introduction.observe(viewLifecycleOwner) {
            signUpViewModel.fetchIntroInfoStatus()
            signUpViewModel.setIsIntroductionValid()
        }
    }


    private fun setupNextButtonListener() {
        binding.btnNext.setOnThrottleClickListener {
            navigateToSelectRoleFragment()
        }
    }


    private fun navigateToSelectRoleFragment() {
        findNavController().navigate(R.id.action_introductionFragment_to_chooseRoleFragment)
    }
}