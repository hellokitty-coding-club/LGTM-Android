package com.lgtm.android.auth.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.android.common_ui.util.NetworkState
import com.lgtm.domain.entity.LgtmResponseException
import com.lgtm.domain.entity.response.GithubLoginResponse
import com.lgtm.domain.repository.AuthRepository
import com.lgtm.domain.repository.LoggingRepository
import com.swm.logging.android.logging_scheme.SWMLoggingScheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val loggingRepository: LoggingRepository
) : BaseViewModel() {

    private val deviceToken = MutableLiveData<String?>()

    private fun getDeviceToken() {
        authRepository.getDeviceToken { deviceToken.value = it }
    }

    init {
        getDeviceToken()
    }

    private val _githubLoginResponse = MutableLiveData<GithubLoginResponse>()
    val githubLoginResponse: LiveData<GithubLoginResponse> = _githubLoginResponse

    fun parseGithubLoginJsonResponse(json: String) {
        val jsonData: String = extractJson(json)
        val response: GithubLoginResponse = parseJsonToGithubLoginResponse(jsonData)
        Log.d(TAG, "GithubLoginJsonResponse: $response")
        _githubLoginResponse.postValue(response)
    }

    fun getMemberDataJson(): String {
        val memberData = githubLoginResponse.value?.memberData
        return Gson().toJson(memberData)
    }

    fun isRegisteredUser(): Boolean {
        return githubLoginResponse.value?.memberData?.registered ?: false
    }

    fun saveMemberDataFromLoginResponse() {
        authRepository.saveUserData(githubLoginResponse.value?.memberData ?: return)
    }

    private fun extractJson(htmlString: String): String {
        val startIndex = htmlString.indexOf('{')
        val endIndex = htmlString.lastIndexOf('}') + 1
        if (startIndex != -1 && endIndex != -1)
            return htmlString.substring(startIndex, endIndex).replace("\\\"", "\"")
        throw IllegalStateException("String does not contain JSON")
    }

    private fun parseJsonToGithubLoginResponse(jsonData: String): GithubLoginResponse {
        return Gson().fromJson(jsonData, GithubLoginResponse::class.java)
    }

    private val _patchDeviceTokenState: MutableLiveData<NetworkState<Boolean>> = MutableLiveData(
        NetworkState.Init
    )
    val patchDeviceTokenState: LiveData<NetworkState<Boolean>> = _patchDeviceTokenState

    fun patchDeviceToken() {
        viewModelScope.launch(lgtmErrorHandler) {
            val deviceToken: String? = deviceToken.value
            authRepository.patchDeviceToken(deviceToken)
                .onSuccess {
                    _patchDeviceTokenState.value = NetworkState.Success(it)
                }.onFailure {
                    Firebase.crashlytics.recordException(it)
                    val errorMessage =
                        if (it is LgtmResponseException) it.message else "푸시 알림 설정 실패"
                    _patchDeviceTokenState.value = NetworkState.Failure(errorMessage)
                }
        }
    }

    fun shotSignInExposureLogging(scheme: SWMLoggingScheme) {
        loggingRepository.shotSwmLogging(scheme)
    }
}
