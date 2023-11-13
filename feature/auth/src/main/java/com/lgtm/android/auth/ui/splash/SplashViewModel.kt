package com.lgtm.android.auth.ui.splash

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.domain.repository.AuthRepository
import com.lgtm.domain.repository.IntroRepository
import com.swm.logging.android.SWMLogging
import com.swm.logging.android.logging_scheme.SWMLoggingScheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val introRepository: IntroRepository,
    private val authRepository: AuthRepository,
) : BaseViewModel() {


    private val _minVersion = MutableLiveData<Int>()
    val minVersion: LiveData<Int> = _minVersion

    private val _latestVersion = MutableLiveData<Int>()
    val latestVersion: LiveData<Int> = _latestVersion

    private val _isSystemMaintenance = MutableLiveData<Boolean>()
    val isSystemMaintenance: LiveData<Boolean> = _isSystemMaintenance


    private val _isDataAllSet = MediatorLiveData<Boolean>().apply { value = false }
    val isDataAllSet: LiveData<Boolean> = _isDataAllSet

    init {
        addSourceOnIsDataAllSet()
    }

    fun getAppVersionInfo() {
        viewModelScope.launch(lgtmErrorHandler) {
            introRepository.getIntro()
                .onSuccess {
                    _minVersion.value = it.minVersion
                    _latestVersion.value = it.latestVersion
                }.onFailure {
                    Firebase.crashlytics.recordException(it)
                    Log.d(TAG, "getAppVersionInfo: $it")
                }
        }
    }

    fun checkSystemMaintenance() {
        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 10
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val isSystemMaintenance = remoteConfig.getBoolean(IS_SYSTEM_MAINTENANCE)
                _isSystemMaintenance.value = isSystemMaintenance
                Log.d(TAG, "checkSystemMaintenance: $isSystemMaintenance")
            } else {
                Log.e(TAG, "checkSystemMaintenance: fail")
                _isSystemMaintenance.value = false
            }
        }
    }

    private fun addSourceOnIsDataAllSet() {
        _isDataAllSet.addSource(minVersion) {
            if (_isDataAllSet.value == true) return@addSource
            fetchIsDataAllSetState()
        }
        _isDataAllSet.addSource(isSystemMaintenance) {
            if (_isDataAllSet.value == true) return@addSource
            fetchIsDataAllSetState()
        }
    }

    private fun fetchIsDataAllSetState() {
        _isDataAllSet.value = minVersion.value != null && isSystemMaintenance.value != null
    }

    fun removeSourceOnIsDataAllSet() {
        _isDataAllSet.removeSource(minVersion)
        _isDataAllSet.removeSource(isSystemMaintenance)
    }

    fun isAutoLoginAvailable(): Boolean {
        return authRepository.isAutoLoginAvailable()
    }

    fun shotSplashExposureLogging(swmLoggingScheme: SWMLoggingScheme) {
        SWMLogging.logEvent(swmLoggingScheme)
    }

    companion object {
        private const val IS_SYSTEM_MAINTENANCE = "isSystemUnderMaintenance"
    }
}