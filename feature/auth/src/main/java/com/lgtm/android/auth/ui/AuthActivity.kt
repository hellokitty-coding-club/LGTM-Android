package com.lgtm.android.auth.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.ActivityAuthBinding
import com.lgtm.android.auth.ui.github.GithubBottomSheet
import com.lgtm.android.auth.ui.signup.SignUpActivity
import com.lgtm.android.common_ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
            when (it.success) {
                true -> moveToNextScreen(it.memberData!!.registered)
                false -> makeToast(it.message)
            }
        }
    }

    private fun makeToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun moveToNextScreen(isRegistered: Boolean) {
        if (!isRegistered) {
            val githubId = authViewModel.githubLoginResponse.value?.memberData?.githubId ?: return
            startActivity(
                Intent(this, SignUpActivity::class.java).putExtra(
                    GITHUB_ID, githubId
                )
            )
        } else {
            // TODO: 메인 화면으로 이동
        }
        finish()
    }

    companion object {
        const val GITHUB_ID = "githubId"
    }
}

