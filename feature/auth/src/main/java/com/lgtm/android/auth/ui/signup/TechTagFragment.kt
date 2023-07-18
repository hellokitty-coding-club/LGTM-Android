package com.lgtm.android.auth.ui.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentTechTagBinding
import com.lgtm.android.common_ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TechTagFragment : BaseFragment<FragmentTechTagBinding>(R.layout.fragment_tech_tag) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
    }

    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }


}