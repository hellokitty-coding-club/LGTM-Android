package com.lgtm.android.auth.ui.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentNicknameBinding
import com.lgtm.android.common_ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NicknameFragment : BaseFragment<FragmentNicknameBinding>(R.layout.fragment_nickname) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        setupEditText()
        onNicknameChanged()
        setupNextButtonListener()
    }

    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }

    private fun onNicknameChanged() {
        signUpViewModel.nickname?.observe(viewLifecycleOwner) {
            signUpViewModel.fetchInfoStatus()
            signUpViewModel.setIsNicknameValid()
            setupEditText()
        }
    }

    private fun setupEditText() {
        binding.etNickname.apply {
            signUpViewModel.nicknameEditTextData.value?.let {
                setEditTextData(it)
            }
        }
    }

    private fun setupNextButtonListener() {
        binding.btnNext.setOnClickListener {
            navigateToTechTagFragment()
        }
    }

    private fun navigateToTechTagFragment() {
        findNavController().navigate(R.id.action_nicknameFragment_to_techTagFragment)
    }

}

