package com.lgtm.android

import android.app.Application
import com.swm.logging.android.SwmLogging
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LGTMApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SwmLogging.init(
            appVersion = BuildConfig.VERSION_NAME,
            osName = ANDROID,
            endpoint = if (BuildConfig.DEBUG) BuildConfig.LGTM_BASE_URL_DEBUG else BuildConfig.LGTM_BASE_URL_RELEASE
        )
    }

    companion object {
        private const val ANDROID = "Android"
    }
}