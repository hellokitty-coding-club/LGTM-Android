package com.lgtm.android.auth.ui.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.FragmentNicknameBinding
import com.lgtm.android.common_ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NicknameFragment : BaseFragment<FragmentNicknameBinding>(R.layout.fragment_nickname) {
    private val signUpViewModel by activityViewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()

        binding.etNickname.apply {
            setEditTextData(
                signUpViewModel.nicknameEditTextData
            )
        }
    }

    private fun setupViewModel() {
        binding.viewModel = signUpViewModel
    }

}

