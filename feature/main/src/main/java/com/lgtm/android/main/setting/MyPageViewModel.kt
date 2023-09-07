package com.lgtm.android.main.setting

import androidx.lifecycle.viewModelScope
import com.lgtm.android.common_ui.base.BaseViewModel
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

    fun clearUserData() {
       authRepository.clearUserData()
    }

    fun getProfileInfo() {
        viewModelScope.launch(lgtmErrorHandler) {
//            profileRepository.getProfileInfo()
//                .onSuccess {
//
//                }.onFailure {
//
//                }
        }
    }

}