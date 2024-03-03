package com.lgtm.android.mission_suggestion.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lgtm.android.common_ui.base.BaseComposeActivity
import com.lgtm.android.common_ui.theme.LGTMTheme
import com.lgtm.android.mission_suggestion.ui.detail.presentation.SuggestionDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuggestionDetailActivity: BaseComposeActivity(){
    private val suggestionDetailViewModel by viewModels<SuggestionDetailViewModel>()
    private val suggestionId by lazy { intent.getIntExtra(SUGGESTION_ID, defaultValue) }

    override fun initializeViewModel() {
        viewModel = suggestionDetailViewModel
    }

    @Composable
    override fun Content() {
        LGTMTheme {
            SuggestionDetailScreen(
                suggestionDetailStateHolder = suggestionDetailViewModel.detailState.collectAsStateWithLifecycle(),
                onBackButtonClick = ::setBackButtonClick
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchSuggestionDetail()
    }
    private fun fetchSuggestionDetail() {
        suggestionDetailViewModel.fetchDetail(suggestionId)
    }

    private fun setBackButtonClick() {
        finish()
    }

    companion object {
        const val SUGGESTION_ID = "suggestion_id"
        private const val defaultValue = -1
    }
}