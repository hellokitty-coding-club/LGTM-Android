package com.lgtm.android.manage_mission

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.lgtm.android.common_ui.adapter.TechTagAdapter
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.manage_mission.databinding.ActivityDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityDashboardBinding>(R.layout.activity_dashboard) {
    private val dashboardViewModel by viewModels<DashboardViewModel>()
    private var missionId by Delegates.notNull<Int>()
    private lateinit var techTagAdapter: TechTagAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getExtraData()
        setupViewModel()
        observeDashboardInfo()
        initAdapter()
        setRecyclerViewLayoutManager()
        setBackButtonClickListener()
    }

    override fun onResume() {
        super.onResume()
        dashboardViewModel.fetchDashboardInfo(missionId)
    }

    private fun setupViewModel() {
        binding.viewModel = dashboardViewModel
    }

    private fun initAdapter() {
        techTagAdapter = TechTagAdapter()
        binding.rvTechTag.adapter = techTagAdapter
    }

    private fun observeDashboardInfo() {
        dashboardViewModel.dashboardInfo.observe(this) {
            techTagAdapter.submitList(it.techTagList)
        }
    }

    private fun setRecyclerViewLayoutManager() {
        val layoutManager = FlexboxLayoutManager(this).apply {
            flexWrap = FlexWrap.WRAP
        }
        binding.rvTechTag.layoutManager = layoutManager
    }

    override fun initializeViewModel() {
        viewModel = dashboardViewModel
    }

    private fun getExtraData() {
        missionId = intent.getIntExtra(MISSION_ID, defaultValue)
    }

    private fun setBackButtonClickListener() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    companion object {
        const val MISSION_ID = "mission_id"
        const val defaultValue = -1
    }
}