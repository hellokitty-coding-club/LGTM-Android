package com.lgtm.android.mission_recommendation.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.mission_recommendation.R
import com.lgtm.android.mission_recommendation.databinding.ActivityRecommendationDashboardBinding

class RecommendationDashboardActivity : BaseActivity<ActivityRecommendationDashboardBinding>(R.layout.activity_recommendation_dashboard) {
    private val recommendationDashboardViewModel by viewModels<RecommendationDashboardViewModel>()
    override fun initializeViewModel() {
        viewModel = recommendationDashboardViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}