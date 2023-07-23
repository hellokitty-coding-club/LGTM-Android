package com.lgtm.android.auth.ui.signup.reviewee

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentEducationStatusBinding
import com.lgtm.android.auth.ui.signup.SignUpViewModel
import com.lgtm.android.common_ui.base.BaseFragment


class EducationStatusFragment :
    BaseFragment<FragmentEducationStatusBinding>(R.layout.fragment_education_status) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
    }

    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }

}