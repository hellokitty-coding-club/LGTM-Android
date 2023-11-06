package com.lgtm.android.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.lgtm.android.common_ui.adapter.SduiAdapter
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.main.R
import com.lgtm.android.main.databinding.FragmentHomeBinding
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
    }

    override fun onResume() {
        super.onResume()
        getHomeInfo()
    }

    private fun getHomeInfo() {
        homeViewModel.getHomeInfo()
    }

    private fun setupViewModel() {
        binding.viewModel = homeViewModel
    }

    private fun setUpNotificationClickListener() {
        binding.ivNotification.setOnClickListener {
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

    private fun moveToNotificationCenter(){
        lgtmNavigator.navigateToNotificationCenter(requireContext())
    }

    private fun submitDataWhenDataChanged() {
        homeViewModel.sduiList.observe(viewLifecycleOwner) {
            commonAdapter.submitList(it)
            homeViewModel.shotHomeExposureLogging()
        }
    }

    private fun onClickNewMissionButton() {
        binding.fabCreateMission.setOnClickListener {
            lgtmNavigator.navigateToCreateMission(requireContext())
        }
    }
}