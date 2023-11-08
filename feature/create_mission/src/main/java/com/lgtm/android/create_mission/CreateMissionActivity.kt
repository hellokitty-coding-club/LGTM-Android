package com.lgtm.android.create_mission

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import com.lgtm.android.common_ui.adapter.ViewPagerAdapter
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.common_ui.util.setOnThrottleClickListener
import com.lgtm.android.create_mission.databinding.ActivityCreateMissionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        disableViewPagerSwipe()
        setOnBackButtonClickListener()
        disableClickDotIndicator()
        setOnBackPressedCallback()
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            setPreviousPage()
        }
    }
    private fun setOnBackPressedCallback() {
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun initAdapter() {
        binding.vpMission.adapter = ViewPagerAdapter(this).also { viewPagerAdapter = it }
        viewPagerAdapter.fragmentList =
            listOf(
                CreateMissionStep1Fragment(),
                CreateMissionStep2Fragment(),
                CreateMissionStep3Fragment(),
                CreateMissionStep4Fragment(),
                CreateMissionStep5Fragment()
            )
    }

    private fun attachViewPagerToIndicator() {
        binding.wormDotsIndicator.attachTo(binding.vpMission)
    }

    private fun disableViewPagerSwipe() {
        binding.vpMission.isUserInputEnabled = false
    }

    private fun setOnBackButtonClickListener() {
        binding.ivBack.setOnThrottleClickListener {
            setPreviousPage()
        }
    }

    private fun disableClickDotIndicator() {
        if (!BuildConfig.DEBUG)
            binding.wormDotsIndicator.dotsClickable = false
    }

    private fun setPreviousPage() {
        val currentItem = binding.vpMission.currentItem
        if (currentItem == 0) finish()
        else binding.vpMission.setCurrentItem(currentItem - 1, true)
    }

    fun setNextPage(){
        val currentItem = binding.vpMission.currentItem
        if (currentItem == 4) finish()
        else binding.vpMission.setCurrentItem(currentItem + 1, true)
    }
}