package com.lgtm.android.profile

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.android.common_ui.model.ProfileGlanceUI
import com.lgtm.android.common_ui.model.mapper.toUiModel
import com.lgtm.domain.usecase.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase
) : BaseViewModel() {

    private val _profileInfo = MutableLiveData<ProfileGlanceUI>()
    val profileInfo : LiveData<ProfileGlanceUI> = _profileInfo

    fun fetchProfileInfo(userId : Int) {
        viewModelScope.launch(lgtmErrorHandler) {
            profileUseCase.fetchProfileInfo(userId)
                .onSuccess {
                    _profileInfo.postValue(it.toUiModel(requireNotNull(it.memberType)))
                    Log.d(TAG, "fetchProfileInfo: $it")
                }.onFailure {
                    Log.e(TAG, "fetchProfileInfo: $it")
                }
        }
    }
}