package com.lgtm.android.profile

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.domain.profile.Profile
import com.lgtm.domain.profile.profileViewType.ProfileGlance
import com.lgtm.domain.profile.profileViewType.ProfileImage
import com.lgtm.domain.usecase.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase
) : BaseViewModel() {

    private val _profileInfo = MutableLiveData<List<Profile>>()
    val profileInfo: LiveData<List<Profile>> = _profileInfo

    private val _isMyProfile = MutableLiveData<Boolean>()

    private var userId: Int? = null

    fun isMyProfile(): Boolean {
        return _isMyProfile.value ?: false
    }

    fun setUserId(userId: Int?) {
        this.userId = userId
    }

    fun getFirstMissionIdx(): Int {
        return profileUseCase.getFirstMissionIdx()
    }

    fun fetchProfileInfo() {
        viewModelScope.launch(lgtmErrorHandler) {
            profileUseCase.fetchProfileInfo(userId)
                .onSuccess {
                    _profileInfo.postValue(it)
                    _isMyProfile.postValue((it[0] as ProfileImage).isMyProfile)
                    Log.d(TAG, "fetchProfileInfo: $it")
                }.onFailure {
                    Firebase.crashlytics.recordException(it)
                    Log.e(TAG, "fetchProfileInfo: $it")
                }
        }
    }

    fun getGithubProfileUrl(): String {
        val gitId = (profileInfo.value?.get(1) as ProfileGlance).githubId
        return "https://github.com/$gitId"
    }
}
