package com.lgtm.android.manage_mission.dashboard

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.lgtm.android.common_ui.adapter.ParticipantAdapter
import com.lgtm.android.common_ui.adapter.TechTagAdapter
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.manage_mission.R
import com.lgtm.android.manage_mission.databinding.ActivityDashboardBinding
import com.lgtm.android.manage_mission.ping_pong_senior.OnBottomSheetDismiss
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
        participantAdapter = ParticipantAdapter(::showPingPongSenior, ::openGithubPullRequestView)
        binding.rvParticipant.adapter = participantAdapter
    }

    private fun showPingPongSenior(memberId: Int) {
        val bottomSheetDialog = PingPongSeniorFragment(
            juniorId = memberId,
            missionId = missionId,
            onDismissListener = object : OnBottomSheetDismiss {
                override fun onBottomSheetDismiss() {
                    dashboardViewModel.fetchDashboardInfo(missionId)
                }
            })
        bottomSheetDialog.show(supportFragmentManager, bottomSheetDialog.tag)
    }

    private fun openGithubPullRequestView(url : String){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun observeDashboardInfo() {
        dashboardViewModel.dashboardInfo.observe(this) {
            techTagAdapter.submitList(it.techTagList)
            participantAdapter.submitList(it.memberInfoList)
            binding.clDashboardEmpty.visibility = if(dashboardViewModel.getDashBoardEmptyVisibility()) VISIBLE else GONE
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