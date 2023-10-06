package com.lgtm.android.manage_mission.ping_pong_junior

import android.widget.Toast
import androidx.activity.viewModels
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.common_ui.util.NetworkState
import com.lgtm.android.manage_mission.R
import com.lgtm.android.manage_mission.databinding.ActivityPingPongJuniorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PingPongJuniorActivity :
    BaseActivity<ActivityPingPongJuniorBinding>(R.layout.activity_ping_pong_junior) {
    private val missionId by lazy { intent.getIntExtra(MISSION_ID, defaultValue) }
    private val pingPongJuniorViewModel by viewModels<PingPongJuniorViewModel>()

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