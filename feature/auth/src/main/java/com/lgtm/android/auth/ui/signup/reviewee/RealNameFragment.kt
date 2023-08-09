package com.lgtm.android.auth.ui.signup.reviewee

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentRealNameBinding
import com.lgtm.android.auth.ui.SignInActivity
import com.lgtm.android.auth.ui.signup.SignUpViewModel
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.common_ui.util.NetworkState


class RealNameFragment : BaseFragment<FragmentRealNameBinding>(R.layout.fragment_real_name) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()
    override fun initializeViewModel() {
        viewModel = signUpViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        setupEditText()
        onRealNameChanged()
        setupCompleteButtonListener()
        observeSignUpStatus()
    }

    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }

    private fun setupEditText() {
        binding.etRealname.apply {
            setLifecycleOwner(viewLifecycleOwner)
            bindEditTextData(signUpViewModel.realNameEditTextData)
        }
    }

    private fun onRealNameChanged() {
        signUpViewModel.realName.observe(viewLifecycleOwner) {
            signUpViewModel.fetchRealNameInfoStatus()
            signUpViewModel.setIsRealNameValid()
        }
    }

    private fun setupCompleteButtonListener() {
        binding.btnComplete.setOnClickListener {
            signUpViewModel.signUpJunior()
        }
    }

    private fun observeSignUpStatus() {
        signUpViewModel.signUpState.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkState.Init -> {
                    /* no-op */
                }

                is NetworkState.Success -> {
                    navigateToHomeActivity()
                    requireActivity().finish()
                }

                is NetworkState.Failure -> {
                    navigateToSignInActivity()
                    requireActivity().finish()
                    Toast.makeText(requireContext(), it.msg, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun navigateToHomeActivity() {
        // todo : navigate to home activity
    }

    private fun navigateToSignInActivity() {
        startActivity(Intent(requireContext(), SignInActivity::class.java))
    }


}