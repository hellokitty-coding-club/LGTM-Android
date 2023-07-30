package com.lgtm.android.auth.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.lgtm.domain.entity.response.GithubLoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {

    private val _githubLoginJsonResponse = MutableLiveData<String>()
    val githubLoginJsonResponse: LiveData<String> = _githubLoginJsonResponse

    private val _githubLoginResponse = MutableLiveData<GithubLoginResponse>()
    val githubLoginResponse: LiveData<GithubLoginResponse> = _githubLoginResponse

    fun setGithubLoginJsonResponse(json: String) {
        _githubLoginJsonResponse.value = json
    }

    fun parseGithubLoginJsonResponse() {
        val json = githubLoginJsonResponse.value ?: return
        val jsonData: String = extractJson(json)
        val response: GithubLoginResponse = parseJsonToGithubLoginResponse(jsonData)
        _githubLoginResponse.postValue(response)
    }

    fun isRegisteredUser(): Boolean {
        return githubLoginResponse.value?.memberData?.registered ?: false
    }

    fun saveMemberDataOnSharedPreference() {
        // todo 이미 LGTM 유저라면 AccessToken, RefreshToken, MemberType을 SharedPreference에 저장
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
