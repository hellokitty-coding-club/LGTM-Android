package com.lgtm.android.auth.ui

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.viewModels
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.ActivitySignInBinding
import com.lgtm.android.auth.ui.github.GithubBottomSheet
import com.lgtm.android.auth.ui.signup.SignUpActivity
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.common_ui.util.NetworkState
import com.lgtm.android.common_ui.util.setOnThrottleClickListener
import com.lgtm.domain.logging.SwmCommonLoggingScheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {

    private val signInViewModel by viewModels<SignInViewModel>()

    override fun initializeViewModel() {
        viewModel = signInViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAnimationOnGithubButton()
        initClickListener()
        observeGithubLoginResponse()
        observePatchDeviceTokenStatus()
        shotSignInExposureLogging()
    }

    private fun setAnimationOnGithubButton() {
        binding.clGithub.startAnimation(
            AnimationUtils.loadAnimation(this, com.lgtm.android.common_ui.R.anim.fade_in_anim)
        )
    }

    private fun initClickListener() {
        binding.clGithub.setOnThrottleClickListener {
            showGithubLoginBottomSheet()
        }
    }

    private fun showGithubLoginBottomSheet() {
        GithubBottomSheet(object : OnLoginSuccess {
            override fun onLoginSuccess(loginResponse: String) {
                signInViewModel.parseGithubLoginJsonResponse(loginResponse)
            }
        }).show(supportFragmentManager, "GithubLoginBottomSheet")
    }

    private fun observeGithubLoginResponse() {
        signInViewModel.githubLoginResponse.observe(this) {
            when (it.success) {
                true -> onGithubLoginSuccess()
                false -> {
                    if (it.responseCode == 20000)
                        Log.e(TAG, "GithubLogin: ${it.message}")
                    makeToast("로그인 실패. 다시 시도해주세요.")
                }
            }
        }
    }

    private fun onGithubLoginSuccess() {
        when (signInViewModel.isRegisteredUser()) {
            true -> { // 순서 보장 중요
                saveMemberData()
                signInViewModel.patchDeviceToken()
            }

            false -> {
                moveToSignUpActivity()
                finish()
            }
        }
    }

    private fun observePatchDeviceTokenStatus() {
        signInViewModel.patchDeviceTokenState.observe(this) {
            when (it) {
                is NetworkState.Init -> {}/* no-op */
                is NetworkState.Success -> {
                    moveToMainActivity()
                    finish()
                }

                is NetworkState.Failure -> {
                    moveToMainActivity()
                    finish()
                    Toast.makeText(this, it.msg, Toast.LENGTH_SHORT).show()
                    // TODO: 푸시알림 설정 실패 - Crashlytics 연동
                }
            }
        }
    }

    private fun moveToMainActivity() {
        lgtmNavigator.navigateToMain(this)
    }

    private fun moveToSignUpActivity() {
        val memberDataJson = signInViewModel.getMemberDataJson()
        startActivity(Intent(this, SignUpActivity::class.java).apply {
            putExtra(MEMBER_DATA, memberDataJson)
        })
    }

    private fun saveMemberData() {
        signInViewModel.saveMemberDataFromLoginResponse()
    }

    private fun makeToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun shotSignInExposureLogging() {
        val scheme = SwmCommonLoggingScheme.Builder()
            .setEventLogName("signInExposure")
            .setScreenName(this.javaClass)
            .build()
        signInViewModel.shotSignInExposureLogging(scheme)
    }


    companion object {
        const val MEMBER_DATA = "memberData"
    }
}

