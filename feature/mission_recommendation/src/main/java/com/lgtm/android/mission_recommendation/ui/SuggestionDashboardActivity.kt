package com.lgtm.android.mission_recommendation.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.mission_recommendation.R
import com.lgtm.android.mission_recommendation.databinding.ActivitySuggestionDashboardBinding

class SuggestionDashboardActivity : BaseActivity<ActivitySuggestionDashboardBinding>(R.layout.activity_suggestion_dashboard) {
    private val suggestionDashboardViewModel by viewModels<SuggestionDashboardViewModel>()
    override fun initializeViewModel() {
        viewModel = suggestionDashboardViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}