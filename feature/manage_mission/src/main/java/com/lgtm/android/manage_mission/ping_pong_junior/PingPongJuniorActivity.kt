package com.lgtm.android.manage_mission.ping_pong_junior

import android.os.Bundle
import androidx.activity.viewModels
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.manage_mission.R
import com.lgtm.android.manage_mission.databinding.ActivityPingPongJuniorBinding

class PingPongJuniorActivity :
    BaseActivity<ActivityPingPongJuniorBinding>(R.layout.activity_ping_pong_junior) {
    private val missionId by lazy { intent.getIntExtra(MISSION_ID, defaultValue) }
    private val pingPongJuniorViewModel by viewModels<PingPongJuniorViewModel>()

    override fun initializeViewModel() {
        viewModel = pingPongJuniorViewModel
    }

    companion object {
        const val MISSION_ID = "mission_id"
        private const val defaultValue = -1
    }
}