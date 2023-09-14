package com.lgtm.android.manage_mission

import android.os.Bundle
import androidx.activity.viewModels
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.manage_mission.databinding.ActivityDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityDashboardBinding>(R.layout.activity_dashboard) {
    private val dashboardViewModel by viewModels<DashboardViewModel>()
    private var missionId by Delegates.notNull<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getExtraData()
    }

    override fun onResume() {
        super.onResume()
        dashboardViewModel.fetchDashboardInfo(missionId)
    }

    override fun initializeViewModel() {
        viewModel = dashboardViewModel
    }

    private fun getExtraData() {
        missionId = intent.getIntExtra(MISSION_ID, defaultValue)
    }

    companion object {
        const val MISSION_ID = "mission_id"
        const val defaultValue = -1
    }
}