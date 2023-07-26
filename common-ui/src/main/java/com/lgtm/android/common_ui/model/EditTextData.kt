package com.lgtm.android.common_ui.model

import androidx.lifecycle.MutableLiveData
import com.lgtm.android.common_ui.constant.InfoType


data class EditTextData(
    val text: MutableLiveData<String>,
    val maxLength: Int,
    val hint: String,
    var infoStatus: MutableLiveData<InfoType> = MutableLiveData(InfoType.NONE)
)
