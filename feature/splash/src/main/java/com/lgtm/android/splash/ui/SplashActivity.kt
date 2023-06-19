package com.lgtm.android.splash.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.lgtm.android.splash.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val splashViewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        observeCurrentVersion()
    }

    override fun onResume() {
        super.onResume()
        splashViewModel.getIntro()
    }

    private fun observeCurrentVersion() {
        splashViewModel.latestVersion.observe(this) {
            Toast.makeText(this, "latestVersion: $it", Toast.LENGTH_LONG).show()
        }
    }


}