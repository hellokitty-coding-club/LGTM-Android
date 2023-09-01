package com.lgtm.android.mission_detail

import androidx.activity.viewModels
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.mission_detail.databinding.ActivityMissionDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MissionDetailActivity : BaseActivity<ActivityMissionDetailBinding>(R.layout.activity_mission_detail) {
    private val missionDetailViewModel by viewModels<MissionDetailViewModel>()

    override fun initializeViewModel() {
        viewModel = missionDetailViewModel
    }

}