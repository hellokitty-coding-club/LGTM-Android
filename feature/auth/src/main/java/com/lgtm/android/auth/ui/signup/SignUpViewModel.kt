package com.lgtm.android.auth.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lgtm.android.common_ui.model.EditTextData
import com.lgtm.android.common_ui.model.InfoType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    private val _githubId = MutableLiveData<String>()
    val githubId: LiveData<String> = _githubId

    fun setGithubId(memberData: String) {
        _githubId.value = memberData
    }

    private val _isAgreeWithTerms = MutableLiveData<Boolean>()
    val isAgreeWithTerms: LiveData<Boolean> = _isAgreeWithTerms

    fun setIsAgreeWithTerms(isAgree: Boolean) {
        _isAgreeWithTerms.value = isAgree
    }

    // 이벤트, 광고성 정보 안내
    private val _isAgreeWithEventInfo = MutableLiveData<Boolean>()
    val isAgreeWithEventInfo: LiveData<Boolean> = _isAgreeWithEventInfo

    fun setIsAgreeWithEventInfo(isAgree: Boolean) {
        _isAgreeWithEventInfo.value = isAgree
    }

    // 닉네임
    private val _nickname = MutableLiveData<String>()
    val nickname: LiveData<String> = _nickname

    fun setNickname(nickname: String) {
        _nickname.value = nickname
    }

    private val _nicknameEditTextData = MutableLiveData(
        EditTextData(
            infoStatus = InfoType.DUPLICATE_NICKNAME,
            maxLength = 10,
            hint = "닉네임을 입력해주세요."
        )
    )
    val nicknameEditTextData: LiveData<EditTextData> = _nicknameEditTextData

    // 이메일
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    // 기술태그
    private val _techTags = MutableLiveData<List<String>>()
    val techTags: LiveData<List<String>> = _techTags

    // 나의 한 줄 소개
    private val _intro = MutableLiveData<String>()
    val intro: LiveData<String> = _intro

    // 실명
    private val _fullName = MutableLiveData<String>()
    val fullName: LiveData<String> = _fullName
}


