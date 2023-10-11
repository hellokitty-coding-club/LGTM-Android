package com.lgtm.android.manage_mission.dashboard

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.lgtm.android.common_ui.adapter.ParticipantAdapter
import com.lgtm.android.common_ui.adapter.TechTagAdapter
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.manage_mission.R
import com.lgtm.android.manage_mission.databinding.ActivityDashboardBinding
import com.lgtm.android.manage_mission.ping_pong_senior.PingPongSeniorFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityDashboardBinding>(R.layout.activity_dashboard) {
    private val dashboardViewModel by viewModels<DashboardViewModel>()
    private val missionId by lazy { intent.getIntExtra(MISSION_ID, defaultValue) }
    private lateinit var techTagAdapter: TechTagAdapter
    private lateinit var participantAdapter: ParticipantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        participantAdapter = ParticipantAdapter(::showPingPongSenior)
        binding.rvParticipant.adapter = participantAdapter
    }

    private fun showPingPongSenior(memberId: Int) {
        val bottomSheetDialog = PingPongSeniorFragment(juniorId = memberId, missionId = missionId)
        bottomSheetDialog.show(supportFragmentManager, bottomSheetDialog.tag)
    }

    private fun observeDashboardInfo() {
        dashboardViewModel.dashboardInfo.observe(this) {
            techTagAdapter.submitList(it.techTagList)
            participantAdapter.submitList(it.memberInfoList)
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