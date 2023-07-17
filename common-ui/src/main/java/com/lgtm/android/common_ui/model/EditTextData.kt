package com.lgtm.android.common_ui.model

import androidx.lifecycle.MutableLiveData


data class EditTextData(
    val text: MutableLiveData<String>,
    val maxLength: Int,
    val hint: String,
    var infoStatus: InfoType
)
