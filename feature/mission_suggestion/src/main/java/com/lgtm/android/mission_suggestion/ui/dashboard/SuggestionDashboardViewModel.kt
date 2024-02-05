package com.lgtm.android.mission_suggestion.ui.dashboard

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.android.common_ui.model.mapper.toUiModel
import com.lgtm.domain.constants.Role
import com.lgtm.domain.mission_suggestion.SuggestionContent
import com.lgtm.domain.mission_suggestion.SuggestionVO
import com.lgtm.domain.mission_suggestion.SuggestionViewType
import com.lgtm.domain.repository.AuthRepository
import com.lgtm.domain.usecase.SuggestionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuggestionDashboardViewModel @Inject constructor(
    private val suggestionUseCase: SuggestionUseCase,
    authRepository: AuthRepository
): BaseViewModel() {

    val suggestionBtnVisibility: Boolean = authRepository.getMemberType() == Role.REVIEWEE

    private val _suggestionList = MutableLiveData<List<SuggestionContent>>()
    val suggestionList: LiveData<List<SuggestionContent>> = _suggestionList

    fun fetchSuggestionList() {
        viewModelScope.launch(lgtmErrorHandler) {
            suggestionUseCase.getSuggestionList()
                .onSuccess {
                    _suggestionList.postValue(convertToUiModel(it))
                    Log.d(TAG, "getSuggestion: $it")
                }.onFailure {
                    Firebase.crashlytics.recordException(it)
                    Log.e(TAG, "getSuggestion: $it")
                }
        }
    }

    private fun convertToUiModel(suggestions: List<SuggestionContent>): List<SuggestionContent> {
        return suggestions.map {
            when(it.viewType) {
               SuggestionViewType.CONTENT -> (it as SuggestionVO).toUiModel()
               else -> it
            }
        }
    }

}