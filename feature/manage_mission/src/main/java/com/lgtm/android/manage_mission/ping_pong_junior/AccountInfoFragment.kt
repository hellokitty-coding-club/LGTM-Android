package com.lgtm.android.manage_mission.ping_pong_junior

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.manage_mission.R
import com.lgtm.android.manage_mission.databinding.FragmentAccountInfoBinding


class AccountInfoFragment :
    BaseFragment<FragmentAccountInfoBinding>(R.layout.fragment_account_info) {
    private val pingPongJuniorViewModel by activityViewModels<PingPongJuniorViewModel>()


    override fun initializeViewModel() {
        viewModel = pingPongJuniorViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = pingPongJuniorViewModel
    }
}