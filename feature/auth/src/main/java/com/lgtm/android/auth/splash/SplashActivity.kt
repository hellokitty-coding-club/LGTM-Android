package com.lgtm.android.auth.splash

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.ActivitySplashBinding
import com.lgtm.android.common_ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private val splashViewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeCurrentVersion()
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


}