package com.lgtm.android.main.setting

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.android.common_ui.model.ProfileGlanceUI
import com.lgtm.android.common_ui.model.mapper.toUiModel
import com.lgtm.domain.repository.AuthRepository
import com.lgtm.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val authRepository: AuthRepository
) : BaseViewModel() {

    private val _profileInfo: MutableLiveData<ProfileGlanceUI> = MutableLiveData()
    val profileInfo: MutableLiveData<ProfileGlanceUI> = _profileInfo

    fun clearUserData() {
        authRepository.clearUserData()
    }

    fun getProfileInfo() {
        viewModelScope.launch(lgtmErrorHandler) {
            profileRepository.getProfileInfo()
                .onSuccess {
                    _profileInfo.postValue(it.toUiModel(requireNotNull(it.memberType)))
                }.onFailure {
                    Log.e(TAG, "getProfileInfo: $it")
                }
        }
    }
}