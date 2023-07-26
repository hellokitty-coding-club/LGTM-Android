package com.lgtm.android.auth.ui.signup.reviewer

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentCareerPeriodBinding
import com.lgtm.android.auth.ui.signup.SignUpViewModel
import com.lgtm.android.common_ui.base.BaseFragment


class CareerPeriodFragment :
    BaseFragment<FragmentCareerPeriodBinding>(R.layout.fragment_career_period) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        onCareerPeriodChanged()
        setupNextButtonListener()
    }


    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }

    private fun onCareerPeriodChanged() {
        binding.etCareerPeriod.addTextChangedListener {
            signUpViewModel.setCareerPeriod(it.toString())
            signUpViewModel.setIsCareerPeriodValid()
        }
    }


    private fun setupNextButtonListener() {
        binding.btnNext.setOnClickListener {
            navigateToBankAccountFragment()
        }
    }

    private fun navigateToBankAccountFragment() {
        // 계좌정보 화면으로 이동
//        findNavController().navigate(R.id.action_careerPeriodFragment_to_bankAccountFragment)
    }

}