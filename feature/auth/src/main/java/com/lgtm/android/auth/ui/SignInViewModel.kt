package com.lgtm.android.auth.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.lgtm.domain.entity.response.GithubLoginResponse
import com.lgtm.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

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
}
