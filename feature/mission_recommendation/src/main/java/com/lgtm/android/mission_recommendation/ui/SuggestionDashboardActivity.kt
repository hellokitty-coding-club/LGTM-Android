package com.lgtm.android.mission_recommendation.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.lgtm.android.common_ui.adapter.MissionSuggestionAdapter
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.common_ui.util.setOnThrottleClickListener
import com.lgtm.android.mission_recommendation.R
import com.lgtm.android.mission_recommendation.databinding.ActivitySuggestionDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuggestionDashboardActivity : BaseActivity<ActivitySuggestionDashboardBinding>(R.layout.activity_suggestion_dashboard) {
    private val suggestionDashboardViewModel by viewModels<SuggestionDashboardViewModel>()
    private lateinit var missionSuggestionAdapter: MissionSuggestionAdapter
    override fun initializeViewModel() {
        viewModel = suggestionDashboardViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBindingData()
        observeMissionSuggestion()
        initAdapter()
        setBackButtonClickListener()
    }

    override fun onResume() {
        super.onResume()
        suggestionDashboardViewModel.getSuggestion()
    }

    private fun setupBindingData() {
        binding.viewModel = suggestionDashboardViewModel
    }

    private fun observeMissionSuggestion() {
        suggestionDashboardViewModel.missionSuggestion.observe(this) {
            missionSuggestionAdapter.submitList(it.suggestions)
        }
    }

    private fun initAdapter() {
        missionSuggestionAdapter = MissionSuggestionAdapter()
        binding.rvMissionSuggestion.adapter = missionSuggestionAdapter
    }

    private fun setBackButtonClickListener() {
        binding.ivBack.setOnThrottleClickListener {
            finish()
        }
    }
}