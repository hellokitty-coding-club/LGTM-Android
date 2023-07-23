package com.lgtm.android.auth.ui.signup.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentChooseRoleBinding
import com.lgtm.android.auth.ui.signup.SignUpViewModel
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.domain.constants.Role
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseRoleFragment : BaseFragment<FragmentChooseRoleBinding>(R.layout.fragment_choose_role) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        observeSelectedRole()
        setupNextButtonListener()
    }

    private fun observeSelectedRole() {
        signUpViewModel.chooseRole.observe(viewLifecycleOwner) {
            signUpViewModel.setIsRoleValid()
        }
    }

    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }

    private fun setupNextButtonListener() {
        binding.btnNext.setOnClickListener {
            if (signUpViewModel.chooseRole.value == Role.REVIEWEE) {
                navigateToEducationStatusFragment()
            } else {
                // 회사 정보 입력 화면으로 이동
            }
        }
    }

    private fun navigateToEducationStatusFragment() {
        findNavController().navigate(R.id.action_chooseRoleFragment_to_educationStatusFragment)
    }

}