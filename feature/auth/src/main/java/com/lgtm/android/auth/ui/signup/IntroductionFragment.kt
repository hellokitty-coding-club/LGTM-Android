package com.lgtm.android.auth.ui.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentIntroductionBinding
import com.lgtm.android.common_ui.base.BaseFragment

class IntroductionFragment :
    BaseFragment<FragmentIntroductionBinding>(R.layout.fragment_introduction) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        setupNextButtonListener()
    }

    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }

    private fun setupNextButtonListener() {
        binding.btnNext.setOnClickListener {
            navigateToSelectRoleFragment()
        }
    }

    private fun navigateToSelectRoleFragment() {
//        findNavController().navigate(R.id.action_techTagFragment_to_introductionFragment)
    }


}