package com.lgtm.android.auth.ui.splash

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lgtm.domain.repository.IntroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val introRepository: IntroRepository
) : ViewModel() {
    private val _minVersion = MutableLiveData<Int>()
    val minVersion: LiveData<Int> = _minVersion

    private val _latestVersion = MutableLiveData<Int>()
    val latestVersion: LiveData<Int> = _latestVersion

    fun getAppVersionInfo() {
        viewModelScope.launch {
            introRepository.getIntro()
                .onSuccess {
                    _minVersion.value = it.minVersion
                    _latestVersion.value = it.latestVersion
                }.onFailure {
                    Log.d(TAG, "getAppVersionInfo: $it")
                }
        }
    }

    fun isAutoLoginAvailable(): Boolean {
        // TODO 로그인 로직 완성할때, sharedPreference 활용해서 세부 로직 작성 예정
        return false
    }
}