package com.lgtm.android.common_ui.model

import androidx.lifecycle.MutableLiveData
import com.lgtm.android.common_ui.constant.InfoType

data class NoLimitEditTextData(
    val text: MutableLiveData<String>,
    val hint: String,
    var infoStatus: MutableLiveData<InfoType> = MutableLiveData(InfoType.NONE),
)
