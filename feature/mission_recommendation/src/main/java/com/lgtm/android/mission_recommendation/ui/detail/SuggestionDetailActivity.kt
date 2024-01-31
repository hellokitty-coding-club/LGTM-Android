package com.lgtm.android.mission_recommendation.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.collectAsState
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.mission_recommendation.R
import com.lgtm.android.mission_recommendation.databinding.ActivitySuggestionDetailBinding
import com.lgtm.android.mission_recommendation.ui.detail.presentation.SuggestionDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuggestionDetailActivity: BaseActivity<ActivitySuggestionDetailBinding>(R.layout.activity_suggestion_detail) {
    private val suggestionDetailViewModel by viewModels<SuggestionDetailViewModel>()
    private val suggestionId by lazy { intent.getIntExtra(SUGGESTION_ID, defaultValue) }

    override fun initializeViewModel() {
        viewModel = suggestionDetailViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchSuggestionDetail()
        binding.composeView.setContent {
            MaterialTheme {
                SuggestionDetailScreen(
                    suggestionDetailStateHolder = suggestionDetailViewModel.detailState.collectAsState()
                )
            }
        }
    }
    private fun fetchSuggestionDetail() {
        suggestionDetailViewModel.fetchDetail(suggestionId)
    }

    companion object {
        const val SUGGESTION_ID = "suggestion_id"
        private const val defaultValue = -1
    }
}