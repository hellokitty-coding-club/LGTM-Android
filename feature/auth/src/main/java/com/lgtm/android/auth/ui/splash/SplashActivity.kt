package com.lgtm.android.auth.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.viewModels
import com.lgtm.android.auth.BuildConfig
import com.lgtm.android.auth.R
import com.lgtm.android.auth.constant.AutoLoginState
import com.lgtm.android.auth.databinding.ActivitySplashBinding
import com.lgtm.android.auth.ui.SignInActivity
import com.lgtm.android.auth.ui.SystemMaintenanceActivity
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.domain.logging.SwmCommonLoggingScheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private val splashViewModel by viewModels<SplashViewModel>()
    private lateinit var autoLoginState: AutoLoginState
    override fun initializeViewModel() {
        viewModel = splashViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeCurrentVersion()
        checkAutoLoginState()
        startNextActivityAfterDelay()
        shotSplashExposureLogging()
    }

    private fun shotSplashExposureLogging() {
        val scheme = SwmCommonLoggingScheme.Builder()
            .setEventLogName("splashExposure")
            .setScreenName(this.javaClass)
            .build()
        viewModel?.shotSwmLogging(scheme)
    }

    override fun onResume() {
        super.onResume()
        splashViewModel.checkSystemMaintenance()
        splashViewModel.getAppVersionInfo()
    }

    private fun observeCurrentVersion() {
        if (BuildConfig.DEBUG) {
            splashViewModel.latestVersion.observe(this) {
                Toast.makeText(this, "latestVersion: $it", Toast.LENGTH_LONG).show()
            }
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
        }, SPLASH_DELAY)
    }

    private fun startNextActivity() {
        splashViewModel.isDataAllSet.observe(this) { isReady ->
            if (isReady == true) {
                when {
                    isSystemMaintenance() -> navigateToSystemMaintenanceActivity()
                    isAutoLoginAvailable() -> moveToMainActivity()
                    isAutoLoginAvailable().not() -> moveToAuthActivity()
                    else -> throw IllegalStateException("Unhandled state")
                }
            }
        }
    }

    private fun isSystemMaintenance() = splashViewModel.isSystemMaintenance.value == true

    private fun isAutoLoginAvailable() = autoLoginState == AutoLoginState.AUTO_LOGIN_SUCCESS

    private fun moveToMainActivity() {
        lgtmNavigator.navigateToMain(this)
        finish()
    }

    private fun navigateToSystemMaintenanceActivity() {
        startActivity(Intent(this, SystemMaintenanceActivity::class.java))
        finish()
    }

    private fun moveToAuthActivity() {
        startActivity(Intent(this, SignInActivity::class.java))
        overridePendingTransition(0, 0)
        finish()
    }

    override fun onDestroy() {
        splashViewModel.removeSourceOnIsDataAllSet()
        super.onDestroy()
    }

    companion object {
        private const val SPLASH_DELAY: Long = 1000
    }
}