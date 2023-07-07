package com.lgtm.android.auth.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.viewModels
import com.lgtm.android.auth.R
import com.lgtm.android.auth.constant.AutoLoginState
import com.lgtm.android.auth.databinding.ActivitySplashBinding
import com.lgtm.android.auth.ui.SignInActivity
import com.lgtm.android.common_ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private val splashViewModel by viewModels<SplashViewModel>()
    private lateinit var autoLoginState: AutoLoginState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeCurrentVersion()
        checkAutoLoginState()
        startNextActivityAfterDelay()
    }

    override fun onResume() {
        super.onResume()
        splashViewModel.getAppVersionInfo()
    }

    private fun observeCurrentVersion() {
        // 추후 강제 업데이트 기능 구현
        splashViewModel.latestVersion.observe(this) {
            Toast.makeText(this, "latestVersion: $it", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkAutoLoginState() {
        autoLoginState = if (splashViewModel.isAutoLoginAvailable())
            AutoLoginState.AUTO_LOGIN_SUCCESS
        else AutoLoginState.AUTO_LOGIN_FAILURE
    }

    private fun startNextActivityAfterDelay() {
        Handler(Looper.getMainLooper()).postDelayed({
            startNextActivity()
        }, Companion.SPLASH_DELAY)
    }

    private fun startNextActivity() {
        if (autoLoginState == AutoLoginState.AUTO_LOGIN_SUCCESS) {
            moveToMainActivity()
        } else {
            moveToAuthActivity()
        }
    }

    private fun moveToMainActivity() {
        // MainActivity로 이동
    }

    private fun moveToAuthActivity() {
        startActivity(Intent(this, SignInActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        })
        overridePendingTransition(0, 0)
        finish()
    }

    companion object {
        private const val SPLASH_DELAY: Long = 1000
    }


}