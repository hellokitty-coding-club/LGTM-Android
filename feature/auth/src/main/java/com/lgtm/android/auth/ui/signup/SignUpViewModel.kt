package com.lgtm.android.auth.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    private val _githubId = MutableLiveData<String>()
    val githubId: LiveData<String> = _githubId

    fun setGithubId(memberData: String) {
        _githubId.value = memberData
    }

    // 이벤트, 광고성 정보 안내
    private val _isAgreeWithEventInfo = MutableLiveData<Boolean>()
    val isAgreeWithEventInfo: LiveData<Boolean> = _isAgreeWithEventInfo

    // 실명
    private val _fullName = MutableLiveData<String>()
    val fullName: LiveData<String> = _fullName

    // 닉네임
    private val _nickname = MutableLiveData<String>()
    val nickname: LiveData<String> = _nickname

    // 이메일
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    // 기술태그
    private val _techTags = MutableLiveData<List<String>>()
    val techTags: LiveData<List<String>> = _techTags

    // 나의 한 줄 소개
    private val _intro = MutableLiveData<String>()
    val intro: LiveData<String> = _intro


}
