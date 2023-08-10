package com.lgtm.android.main.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.main.R
import com.lgtm.android.main.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun initializeViewModel() {
        TODO("Not yet implemented")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpNotificationClickListener()
    }

    private fun setUpNotificationClickListener() {
        binding.ivNotification.setOnClickListener {
            Toast.makeText(requireContext(), "서비스 준비 중입니다 :)", Toast.LENGTH_SHORT).show()
        }
    }


}