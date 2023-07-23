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
        setOnRadioButtonClickListener()
    }

    private fun setOnRadioButtonClickListener() {
        binding.rgEduStatus.setOnCheckedChangeListener { _, id ->
            val idx = when (id) {
                R.id.rb_high_school_student -> 0
                R.id.rb_undergraduate -> 1
                R.id.rb_graduate -> 2
                R.id.rb_office_workers -> 3
                else -> return@setOnCheckedChangeListener
            }
            signUpViewModel.setEducationStatus(idx)
            signUpViewModel.setIsEducationStatusValid()
        }
    }

    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }

}