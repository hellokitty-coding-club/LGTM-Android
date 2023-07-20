package com.lgtm.android.auth.ui.signup.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentSelectRoleBinding
import com.lgtm.android.auth.ui.signup.SignUpViewModel
import com.lgtm.android.common_ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectRoleFragment : BaseFragment<FragmentSelectRoleBinding>(R.layout.fragment_select_role) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
    }

    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }

}