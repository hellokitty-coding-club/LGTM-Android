package com.lgtm.android.main.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.lgtm.android.common_ui.adapter.SduiAdapter
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.common_ui.util.setOnThrottleClickListener
import com.lgtm.android.main.R
import com.lgtm.android.main.databinding.FragmentHomeBinding
import com.lgtm.domain.constants.Role
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val homeViewModel by viewModels<HomeViewModel>()
    private lateinit var commonAdapter: SduiAdapter

    override fun initializeViewModel() {
        viewModel = homeViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        initAdapter()
        submitDataWhenDataChanged()
        setUpNotificationClickListener()
        onClickNewMissionButton()
        onClickServiceGuideButton()
        observeNewNotification()
    }

    override fun onResume() {
        super.onResume()
        getHomeInfo()
        checkNewNotification()
    }

    private fun getHomeInfo() {
        homeViewModel.getHomeInfo()
    }

    private fun checkNewNotification() {
        homeViewModel.hasNewNotification()
    }

    private fun observeNewNotification() {
        homeViewModel.hasNewNotification.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.ivNewNotification.visibility = View.VISIBLE
            } else {
                binding.ivNewNotification.visibility = View.GONE
            }
        }
    }

    private fun setupViewModel() {
        binding.viewModel = homeViewModel
    }

    private fun setUpNotificationClickListener() {
        binding.ivNotification.setOnThrottleClickListener {
            moveToNotificationCenter()
        }
    }

    private fun initAdapter() {
        commonAdapter = SduiAdapter(::moveToMissionDetail)
        binding.rvSdui.adapter = commonAdapter
    }

    private fun moveToMissionDetail(missionId: Int) {
        lgtmNavigator.navigateToMissionDetail(requireContext(), missionId)
    }

    private fun moveToNotificationCenter() {
        lgtmNavigator.navigateToNotificationCenter(requireContext())
    }

    private fun submitDataWhenDataChanged() {
        homeViewModel.sduiList.observe(viewLifecycleOwner) {
            commonAdapter.submitList(it)
            homeViewModel.shotHomeExposureLogging()
        }
    }

    private fun onClickNewMissionButton() {
        binding.fabCreateMission.setOnThrottleClickListener {
            lgtmNavigator.navigateToCreateMission(requireContext())
        }
    }

    private fun onClickServiceGuideButton() {
        val role = homeViewModel.getUserRole()
        binding.ivServiceGuide.setOnThrottleClickListener {
            val url = when (role) {
                Role.REVIEWER -> "https://www.notion.so/team-hkcc/c1933575315e4b50aedbdd6b39069d3e?pvs=4"
                Role.REVIEWEE -> "https://www.notion.so/team-hkcc/5abf8b03763a4f0d90cb2d463f6d46b4?pvs=4"
            }
            openUrlInBrowser(url)
        }
    }

    private fun openUrlInBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }

}