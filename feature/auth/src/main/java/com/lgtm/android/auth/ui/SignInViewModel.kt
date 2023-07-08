package com.lgtm.android.auth.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.lgtm.android.auth.model.GithubLoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {
    private val _githubLoginResponse = MutableLiveData<GithubLoginResponse>()
    val githubLoginResponse: LiveData<GithubLoginResponse> = _githubLoginResponse

    fun getGithubId(): String {
        return githubLoginResponse.value?.memberData?.githubId ?: ""
    }

    fun parseAndSetGithubLoginResponse(loginResponse: String) {
        val jsonData = extractJson(loginResponse)
        val response = parseJsonToGithubLoginResponse(jsonData)
        _githubLoginResponse.postValue(response)
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
