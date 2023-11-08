package com.lgtm.android.auth.ui.signup.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentChooseRoleBinding
import com.lgtm.android.auth.ui.signup.SignUpViewModel
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.common_ui.util.setOnThrottleClickListener
import com.lgtm.domain.constants.Role
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseRoleFragment : BaseFragment<FragmentChooseRoleBinding>(R.layout.fragment_choose_role) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()
    override fun initializeViewModel() {
        viewModel = signUpViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        observeSelectedRole()
        setupNextButtonListener()
    }

    private fun observeSelectedRole() {
        signUpViewModel.selectedRole.observe(viewLifecycleOwner) {
            signUpViewModel.setIsRoleValid()
        }
    }

    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }

    private fun setupNextButtonListener() {
        binding.btnNext.setOnThrottleClickListener {
            if (signUpViewModel.selectedRole.value == Role.REVIEWEE) {
                navigateToEducationStatusFragment()
            } else {
                navigateToCompanyNameFragment()
            }
        }
    }

    private fun navigateToEducationStatusFragment() {
        findNavController().navigate(R.id.action_chooseRoleFragment_to_educationStatusFragment)
    }

    private fun navigateToCompanyNameFragment() {
        findNavController().navigate(R.id.action_chooseRoleFragment_to_companyNameFragment)
    }

}