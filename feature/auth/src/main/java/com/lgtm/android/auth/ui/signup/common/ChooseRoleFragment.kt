package com.lgtm.android.auth.ui.signup.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentChooseRoleBinding
import com.lgtm.android.auth.ui.signup.SignUpViewModel
import com.lgtm.android.common_ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseRoleFragment : BaseFragment<FragmentChooseRoleBinding>(R.layout.fragment_choose_role) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        observeSelectedRole()
    }

    private fun observeSelectedRole() {
        signUpViewModel.selectedRole.observe(viewLifecycleOwner) {
            signUpViewModel.setIsRoleValid()
        }
    }

    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }

}