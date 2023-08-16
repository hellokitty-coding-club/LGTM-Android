package com.lgtm.android.create_mission

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.android.common_ui.constant.InfoType
import com.lgtm.android.common_ui.model.EditTextData
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class CreateMissionViewModel @Inject constructor() : BaseViewModel() {

    val missionTitleEditTextData = MutableLiveData(
        EditTextData(
            text = MutableLiveData(""),
            infoStatus = MutableLiveData(InfoType.NONE),
            maxLength = 100,
            hint = "제목을 입력하세요."
        )
    )

    val missionRepoUrlEditTextData = MutableLiveData(
        EditTextData(
            text = MutableLiveData(""),
            infoStatus = MutableLiveData(InfoType.NONE),
            maxLength = 5000,
            hint = "URL을 입력하세요.",
            wordCountVisibility = false
        )
    )

    val missionTitle: LiveData<String> =
        missionTitleEditTextData.value?.text ?: MutableLiveData("")

    fun updateMissionTitleInfoStatus() {
        if (missionTitle.value?.isBlank() == true && missionTitle.value?.isNotEmpty() == true)
            missionTitleEditTextData.value?.infoStatus?.value = InfoType.SPACE_ONLY_NOT_ALLOWED
        else
            missionTitleEditTextData.value?.infoStatus?.value = InfoType.NONE
    }

    private fun isMissionTitleValid(): Boolean {
        return missionTitleEditTextData.value?.infoStatus?.value == InfoType.NONE
                && missionTitleEditTextData.value?.text?.value?.isNotBlank() == true
    }

    val missionRepoUrl: LiveData<String> =
        missionRepoUrlEditTextData.value?.text ?: MutableLiveData("")

    fun updateMissionRepoUrlInfoStatus() {
        if (missionRepoUrl.value?.isBlank() == true && missionRepoUrl.value?.isNotEmpty() == true)
            missionRepoUrlEditTextData.value?.infoStatus?.value = InfoType.SPACE_ONLY_NOT_ALLOWED
        else if (missionRepoUrl.value?.isNotBlank() == true && !isGithubUrl(missionRepoUrl.value!!))
            missionRepoUrlEditTextData.value?.infoStatus?.value = InfoType.GITHUB_URL_ONLY
        else
            missionRepoUrlEditTextData.value?.infoStatus?.value = InfoType.NONE
    }

    private fun isMissionRepoUrlValid(): Boolean {
        return missionRepoUrlEditTextData.value?.infoStatus?.value == InfoType.NONE
                && missionRepoUrlEditTextData.value?.text?.value?.isNotBlank() == true
    }

    private fun isGithubUrl(url: String): Boolean {
        val pattern = "https://github\\.com/"
        val regex = Pattern.compile(pattern)
        val matcher = regex.matcher(url)
        return matcher.find()
    }


    private val _isStep1DataValid = MutableLiveData<Boolean>()
    val isStep1DataValid: LiveData<Boolean> = _isStep1DataValid

    fun setIsStep1DataValid() {
        _isStep1DataValid.value = isMissionTitleValid() && isMissionRepoUrlValid()
    }
}