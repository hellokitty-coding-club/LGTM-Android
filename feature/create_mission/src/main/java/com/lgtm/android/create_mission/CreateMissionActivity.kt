package com.lgtm.android.create_mission

import android.os.Bundle
import androidx.activity.viewModels
import com.lgtm.android.common_ui.adapter.ViewPagerAdapter
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.create_mission.databinding.ActivityCreateMissionBinding

class CreateMissionActivity :
    BaseActivity<ActivityCreateMissionBinding>(R.layout.activity_create_mission) {
    private val createMissionViewModel by viewModels<CreateMissionViewModel>()
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun initializeViewModel() {
        viewModel = createMissionViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
        attachViewPagerToIndicator()
    }

    private fun initAdapter() {
        binding.vpMission.adapter = ViewPagerAdapter(this).also { viewPagerAdapter = it }
        viewPagerAdapter.fragmentList =
            listOf(
                CreateMissionStep1Fragment(),
                CreateMissionStep1Fragment(), // todo fragment 교체
                CreateMissionStep1Fragment(),
                CreateMissionStep1Fragment(),
                CreateMissionStep1Fragment()
            )
    }

    private fun attachViewPagerToIndicator() {
        binding.wormDotsIndicator.attachTo(binding.vpMission)
    }
}