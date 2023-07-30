package com.lgtm.android.auth.ui

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.viewModels
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.ActivitySignInBinding
import com.lgtm.android.auth.ui.github.GithubBottomSheet
import com.lgtm.android.auth.ui.signup.SignUpActivity
import com.lgtm.android.common_ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {

    private val signInViewModel by viewModels<SignInViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAnimationOnGithubButton()
        initClickListener()
        observeGithubLoginResponse()
    }

    private fun setAnimationOnGithubButton() {
        binding.clGithub.startAnimation(
            AnimationUtils.loadAnimation(this, com.lgtm.android.common_ui.R.anim.fade_in_anim)
        )
    }

    private fun initClickListener() {
        binding.clGithub.setOnClickListener {
            showGithubLoginBottomSheet()
        }
    }

    private fun showGithubLoginBottomSheet() {
        GithubBottomSheet(object : OnLoginSuccess {
            override fun onLoginSuccess(loginResponse: String) {
                signInViewModel.setGithubLoginJsonResponse(loginResponse)
                signInViewModel.parseGithubLoginJsonResponse()
            }
        }).show(supportFragmentManager, "GithubLoginBottomSheet")
    }

    private fun observeGithubLoginResponse() {
        signInViewModel.githubLoginResponse.observe(this) {
            when (it.success) {
                true -> onGithubLoginSuccess()
                false -> makeToast("로그인 실패. 다시 시도해주세요.")
            }
        }
    }

    private fun onGithubLoginSuccess() {
        when (signInViewModel.isRegisteredUser()) {
            true -> {
                moveToMainActivity()
                saveMemberData()
            }

            false -> moveToSignUpActivity()
        }
        finish()
    }

    private fun moveToMainActivity() {
        // todo move to mainActivity
    }

    private fun moveToSignUpActivity() {
        val githubLoginResponseJson = signInViewModel.githubLoginJsonResponse.value ?: return
        startActivity(Intent(this, SignUpActivity::class.java).apply {
            putExtra(GITHUB_ID, githubLoginResponseJson)
        })
    }

    private fun saveMemberData() {
        signInViewModel.saveMemberDataOnSharedPreference()
    }

    private fun makeToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val GITHUB_ID = "githubId"
    }
}

