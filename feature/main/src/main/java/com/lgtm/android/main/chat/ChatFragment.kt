package com.lgtm.android.main.chat

import androidx.fragment.app.viewModels
import com.lgtm.android.common_ui.base.BaseFragment
import com.lgtm.android.main.R
import com.lgtm.android.main.databinding.FragmentChatBinding
import com.lgtm.android.main.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ChatFragment : BaseFragment<FragmentChatBinding>(R.layout.fragment_chat) {
    private val homeViewModel by viewModels<HomeViewModel>()
    override fun initializeViewModel() {
        viewModel = homeViewModel // todo : homeViewModel -> chatViewModel
    }
}