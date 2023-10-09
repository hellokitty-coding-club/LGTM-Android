package com.lgtm.android.manage_mission.ping_pong_junior

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.lgtm.android.common_ui.adapter.TechTagAdapter
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.common_ui.util.NetworkState
import com.lgtm.android.manage_mission.R
import com.lgtm.android.manage_mission.databinding.ActivityPingPongJuniorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PingPongJuniorActivity :
    BaseActivity<ActivityPingPongJuniorBinding>(R.layout.activity_ping_pong_junior) {
    private lateinit var techTagAdapter: TechTagAdapter
    private val missionId by lazy { intent.getIntExtra(MISSION_ID, defaultValue) }
    private val pingPongJuniorViewModel by viewModels<PingPongJuniorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        initAdapter()
        observeMissionStatusInfo()
        setRecyclerViewLayoutManager()
        setBackButtonClickListener()
    }

    private fun setupViewModel() {
        binding.viewModel = pingPongJuniorViewModel
    }

    private fun initAdapter() {
        techTagAdapter = TechTagAdapter()
        binding.rvTechTag.adapter = techTagAdapter
    }

    private fun observeMissionStatusInfo() {
        pingPongJuniorViewModel.pingPongJuniorVO.observe(this) {
            techTagAdapter.submitList(it.techTagList)
            setMissionProcessData()
        }
    }

    private fun setMissionProcessData(){
        binding.missionStatus.setData(
            role = pingPongJuniorViewModel.getRole(),
            missionStatus = pingPongJuniorViewModel.getMissionStatus(),
            missionHistory = pingPongJuniorViewModel.getMissionHistory()
        )
    }

    private fun setRecyclerViewLayoutManager() {
        val layoutManager = FlexboxLayoutManager(this).apply {
            flexWrap = FlexWrap.WRAP
        }
        binding.rvTechTag.layoutManager = layoutManager
    }


    private fun setBackButtonClickListener() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    override fun initializeViewModel() {
        viewModel = pingPongJuniorViewModel
    }

    override fun onResume() {
        super.onResume()
        fetchJuniorMissionStatus()
        observeFetchMissionStatusState()
    }

    private fun fetchJuniorMissionStatus() {
        pingPongJuniorViewModel.fetchJuniorMissionStatus(missionID = missionId)
    }

    private fun observeFetchMissionStatusState() {
        pingPongJuniorViewModel.fetchMissionStatusState.observe(this) {
            when (it) {
                is NetworkState.Init -> {
                    // do nothing
                }

                is NetworkState.Success -> {
                    // do nothing
                }

                is NetworkState.Failure -> {
                    Toast.makeText(this, "${it.msg}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        const val MISSION_ID = "mission_id"
        private const val defaultValue = -1
    }
}