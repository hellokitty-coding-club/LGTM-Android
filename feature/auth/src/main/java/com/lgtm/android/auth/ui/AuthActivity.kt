package com.lgtm.android.auth.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.ActivityAuthBinding
import com.lgtm.android.common_ui.base.BaseActivity

class AuthActivity : BaseActivity<ActivityAuthBinding>(R.layout.activity_auth) {
    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initClickListener()
        observeGithubLoginResponse()
    }

    private fun initClickListener() {
        binding.clGithub.setOnClickListener {
            GithubBottomSheet(object : OnLoginSuccess {
                override fun onLoginSuccess(loginResponse: String) {
                    authViewModel.parseAndSetGithubLoginResponse(loginResponse)
                }
            }).show(supportFragmentManager, "GithubLoginBottomSheet")
        }
    }

    private fun observeGithubLoginResponse() {
        authViewModel.githubLoginResponse.observe(this) {
            // 로그인 / 회원가입 뷰 전환 분기
            Log.d(TAG, "observeLoginData: $it")
        }
    }
}

