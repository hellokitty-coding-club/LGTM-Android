package com.lgtm.android.auth.ui.signup

import android.os.Bundle
import androidx.activity.viewModels
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.ActivitySignUpBinding
import com.lgtm.android.auth.ui.SignInActivity.Companion.MEMBER_DATA
import com.lgtm.android.common_ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val signUpViewModel by viewModels<SignUpViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getExtraData()
    }

    override fun initializeViewModel() {
        viewModel = signUpViewModel
    }

    private fun getExtraData() {
        val memberDataJson = intent.getStringExtra(MEMBER_DATA) ?: return
        signUpViewModel.parseAndSetMemberDataJson(memberDataJson)
    }
}