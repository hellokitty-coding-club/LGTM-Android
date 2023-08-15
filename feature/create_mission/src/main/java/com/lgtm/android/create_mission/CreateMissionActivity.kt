package com.lgtm.android.create_mission

import android.os.Bundle
import androidx.activity.viewModels
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.create_mission.databinding.ActivityCreateMissionBinding

class CreateMissionActivity :
    BaseActivity<ActivityCreateMissionBinding>(R.layout.activity_create_mission) {
    private val createMissionViewModel by viewModels<CreateMissionViewModel>()
    override fun initializeViewModel() {
        viewModel = createMissionViewModel
    }

}