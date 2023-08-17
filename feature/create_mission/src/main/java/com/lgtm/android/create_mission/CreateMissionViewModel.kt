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

    private fun isConsistOnlyWithSpace(liveData: LiveData<String>) =
        (liveData.value?.isBlank() == true && liveData.value?.isNotEmpty() == true)


    /** step 1 */

    val titleEditTextData = MutableLiveData(
        EditTextData(
            text = MutableLiveData(""),
            infoStatus = MutableLiveData(InfoType.NONE),
            maxLength = 100,
            hint = "제목을 입력하세요."
        )
    )

    val repoUrlEditTextData = MutableLiveData(
        EditTextData(
            text = MutableLiveData(""),
            infoStatus = MutableLiveData(InfoType.NONE),
            maxLength = 5000,
            hint = "URL을 입력하세요.",
            wordCountVisibility = false
        )
    )

    val title: LiveData<String> =
        titleEditTextData.value?.text ?: MutableLiveData("")

    fun updateMissionTitleInfoStatus() {
        if (isConsistOnlyWithSpace(title))
            titleEditTextData.value?.infoStatus?.value = InfoType.SPACE_ONLY_NOT_ALLOWED
        else
            titleEditTextData.value?.infoStatus?.value = InfoType.NONE
    }

    private fun isTitleValid(): Boolean {
        return titleEditTextData.value?.infoStatus?.value == InfoType.NONE
                && titleEditTextData.value?.text?.value?.isNotBlank() == true
    }

    val repositoryUrl: LiveData<String> =
        repoUrlEditTextData.value?.text ?: MutableLiveData("")

    fun updateMissionRepoUrlInfoStatus() {
        if (isConsistOnlyWithSpace(repositoryUrl))
            repoUrlEditTextData.value?.infoStatus?.value = InfoType.SPACE_ONLY_NOT_ALLOWED
        else if (repositoryUrl.value?.isNotBlank() == true && !isGithubUrl(
                repositoryUrl.value ?: ""
            )
        )
            repoUrlEditTextData.value?.infoStatus?.value = InfoType.GITHUB_URL_ONLY
        else
            repoUrlEditTextData.value?.infoStatus?.value = InfoType.NONE
    }

    private fun isMissionRepoUrlValid(): Boolean {
        return repoUrlEditTextData.value?.infoStatus?.value == InfoType.NONE
                && repoUrlEditTextData.value?.text?.value?.isNotBlank() == true
    }

    private fun isGithubUrl(url: String): Boolean {
        // todo webview에서 https 없이도 열리는지 확인하고 https 없이도 열리는 경우에는 pattern 수정
        val pattern = "https://github\\.com/"
        val regex = Pattern.compile(pattern)
        val matcher = regex.matcher(url)
        return matcher.find()
    }


    private val _isStep1DataValid = MutableLiveData<Boolean>()
    val isStep1DataValid: LiveData<Boolean> = _isStep1DataValid

    fun setIsStep1DataValid() {
        _isStep1DataValid.value = isTitleValid() && isMissionRepoUrlValid()
    }

    /** step2 */
    val techTagList = MutableLiveData<MutableList<String>>(mutableListOf())

    private val _isStep2DataValid = MutableLiveData<Boolean>()
    val isStep2DataValid: LiveData<Boolean> = _isStep2DataValid

    fun setIsTechTagValid() {
        _isStep2DataValid.value = (techTagList.value?.size ?: 0) > 0
    }
}