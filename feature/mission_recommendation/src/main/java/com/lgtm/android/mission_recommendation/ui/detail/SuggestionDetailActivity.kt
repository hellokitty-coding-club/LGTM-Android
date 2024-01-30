package com.lgtm.android.mission_recommendation.ui.detail

import androidx.activity.viewModels
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.mission_recommendation.R
import com.lgtm.android.mission_recommendation.databinding.ActivitySuggestionDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuggestionDetailActivity: BaseActivity<ActivitySuggestionDetailBinding>(R.layout.activity_suggestion_detail) {
    private val suggestionDetailViewModel by viewModels<SuggestionDetailViewModel>()
    override fun initializeViewModel() {
        viewModel = suggestionDetailViewModel
    }
}