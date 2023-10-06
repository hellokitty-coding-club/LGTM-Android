package com.lgtm.android

import android.app.Application
import com.lgtm.android.data.datasource.LgtmPreferenceDataSource
import com.swm.logging.android.SWMLogging
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class LGTMApplication : Application() {

    @Inject
    lateinit var lgtmPreferenceDataSource: LgtmPreferenceDataSource

    override fun onCreate() {
        super.onCreate()
        SWMLogging.init(
            appVersion = BuildConfig.VERSION_NAME,
            osNameAndVersion = "$ANDROID ${android.os.Build.VERSION.SDK_INT}",
            baseUrl = if (BuildConfig.DEBUG) BuildConfig.LGTM_BASE_URL_DEBUG else BuildConfig.LGTM_BASE_URL_RELEASE,
            serverPath = "v1/log",
            token = getAuthToken()
        )
    }

    private fun getAuthToken(): String {
        return lgtmPreferenceDataSource.getValue(
            preferenceKey = LgtmPreferenceDataSource.Companion.PreferenceKey.ACCESS_TOKEN,
            defaultValue = "",
            isEncrypted = true
        )
    }

    companion object {
        private const val ANDROID = "Android"
    }
}