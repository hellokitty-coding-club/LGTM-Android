package com.lgtm.android.auth.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lgtm.android.common_ui.constant.InfoType
import com.lgtm.android.common_ui.model.EditTextData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    /** Github Id */
    private val _githubId = MutableLiveData<String>()
    val githubId: LiveData<String> = _githubId

    fun setGithubId(memberData: String) {
        _githubId.value = memberData
    }

    /** 약관 동의 */
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


    /** 닉네임 */
    val nicknameEditTextData = MutableLiveData(
        EditTextData(
            text = MutableLiveData(""),
            infoStatus = MutableLiveData(InfoType.NONE),
            maxLength = 10,
            hint = "닉네임을 입력해주세요."
        )
    )

    val nickname: LiveData<String> = nicknameEditTextData.value?.text
        ?: throw IllegalArgumentException("nickname cannot be null")

    fun fetchNicknameInfoStatus() {
        val regex = Regex("\\s")
        if (regex.containsMatchIn(nickname.value ?: "")) {
            nicknameEditTextData.value?.infoStatus?.value = InfoType.NO_SPACE
        } else {
            nicknameEditTextData.value?.infoStatus?.value = InfoType.NONE
        }
    }

    private val _isNicknameValid = MutableLiveData<Boolean>()
    val isNicknameValid: LiveData<Boolean> = _isNicknameValid

    fun setIsNicknameValid() {
        _isNicknameValid.value =
            (nicknameEditTextData.value?.infoStatus?.value == InfoType.NONE) && (nickname.value?.isNotBlank() == true)
    }

    /** 기술 태그 목록 */
    val techTagList = MutableLiveData<MutableList<String>>(mutableListOf())

    private val _isTechTagValid = MutableLiveData<Boolean>()
    val isTechTagValid: LiveData<Boolean> = _isTechTagValid

    fun setIsTechTagValid() {
        _isTechTagValid.value = (techTagList.value?.size ?: 0) > 0
    }

    /** 한줄 소개 */
    val introEditTextData = MutableLiveData(
        EditTextData(
            text = MutableLiveData(""),
            infoStatus = MutableLiveData(InfoType.NONE),
            maxLength = 40,
            hint = "내용을 입력하세요"
        )
    )

    val introduction: LiveData<String> = introEditTextData.value?.text
        ?: throw IllegalArgumentException("introduction cannot be null")

    private val _isIntroductionValid = MutableLiveData<Boolean>()
    val isIntroductionValid: LiveData<Boolean> = _isIntroductionValid

    fun setIsIntroductionValid() {
        _isIntroductionValid.value =
            (introEditTextData.value?.infoStatus?.value == InfoType.NONE) && (introEditTextData.value?.text?.value?.isNotBlank() == true)
    }

    fun fetchIntroInfoStatus() {
        if (introduction.value?.isBlank() == true && introduction.value?.isNotEmpty() == true)
            introEditTextData.value?.infoStatus?.value = InfoType.SPACE_ONLY_NOT_ALLOWED
        else
            introEditTextData.value?.infoStatus?.value = InfoType.NONE
    }


    // 이메일
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    // 나의 한 줄 소개
    private val _intro = MutableLiveData<String>()
    val intro: LiveData<String> = _intro

    // 실명
    private val _fullName = MutableLiveData<String>()
    val fullName: LiveData<String> = _fullName
}


