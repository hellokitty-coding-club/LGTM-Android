package com.lgtm.android.create_mission

import androidx.lifecycle.MutableLiveData
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.android.common_ui.constant.InfoType
import com.lgtm.android.common_ui.model.EditTextData
import java.util.regex.Pattern

class CreateMissionViewModel : BaseViewModel() {

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

class CreateMissionViewModel : BaseViewModel()