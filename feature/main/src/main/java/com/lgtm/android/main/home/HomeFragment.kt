package com.lgtm.android.main.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
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
        homeViewModel.getHomeInfo()
        initAdapter()
        submitDataWhenDataChanged()
        setUpNotificationClickListener()
    }

    private fun setUpNotificationClickListener() {
        binding.ivNotification.setOnClickListener {
            Toast.makeText(requireContext(), "서비스 준비 중입니다 :)", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initAdapter() {
        commonAdapter = SduiAdapter()
        binding.rvSdui.adapter = commonAdapter
    }

    private fun submitDataWhenDataChanged() {
        homeViewModel.sduiList.observe(viewLifecycleOwner) {
            commonAdapter.submitList(it)
        }
    }
}