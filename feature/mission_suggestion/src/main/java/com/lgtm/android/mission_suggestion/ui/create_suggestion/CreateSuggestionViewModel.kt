package com.lgtm.android.mission_suggestion.ui.create_suggestion

import androidx.lifecycle.MutableLiveData
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.android.common_ui.constant.InfoType
import com.lgtm.android.common_ui.model.EditTextData
import com.lgtm.android.common_ui.model.NoLimitEditTextData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CreateSuggestionViewModel @Inject constructor(): BaseViewModel() {
    private val _suggestionTitleTextData = MutableStateFlow(
        EditTextData(
            text = MutableLiveData(""),
            infoStatus = MutableLiveData(InfoType.TITLE_REQUIRED),
            maxLength = 100,
            hint = "제목을 입력하세요."
        )
    )
    val suggestionTitleTextData: StateFlow<EditTextData> = _suggestionTitleTextData

    private val _suggestionContentTextData = MutableStateFlow(
        NoLimitEditTextData(
            text = MutableLiveData(""),
            infoStatus = MutableLiveData(InfoType.CONTENT_REQUIRED),
            hint = "본문을 입력하세요."
        )
    )
    val suggestionContentTextData: StateFlow<NoLimitEditTextData> = _suggestionContentTextData

    private val _isSuggestionValid = MutableStateFlow(false)
    val isSuggestionValid: StateFlow<Boolean> =_isSuggestionValid

    fun updateSuggestionTitleTextData() {
        _suggestionTitleTextData.value.infoStatus.value =
            if ((this._suggestionTitleTextData.value.text.value ?: "").isBlank()) {
                InfoType.TITLE_REQUIRED
            } else {
                InfoType.NONE
            }
        setIsSuggestionValid()
    }

    fun updateSuggestionContentTextData() {
        _suggestionContentTextData.value.infoStatus.value =
            if ((this._suggestionContentTextData.value.text.value ?: "").isBlank()) {
                InfoType.CONTENT_REQUIRED
            } else {
                InfoType.NONE
            }
        setIsSuggestionValid()
    }

    private fun setIsSuggestionValid() {
        _isSuggestionValid.value =
            (_suggestionTitleTextData.value.infoStatus.value == InfoType.NONE) && (_suggestionContentTextData.value.infoStatus.value == InfoType.NONE)
    }
}