package com.lgtm.android.manage_mission.ping_pong_junior

import android.content.ClipData
import android.content.ClipboardManager
import android.content.ContentValues.TAG
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.lgtm.android.common_ui.R.string
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
        setUpViewModel()
        onClickAccountInfo()
    }

    private fun setUpViewModel() {
        binding.viewModel = pingPongJuniorViewModel
    }

    private fun onClickAccountInfo() {
        binding.clAccountInfo.setOnClickListener {
            try {
                val clipboard =
                    requireActivity().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("label", pingPongJuniorViewModel.getAccountInfo())
                clipboard.setPrimaryClip(clip)
                Toast.makeText(requireContext(), getString(string.copied), Toast.LENGTH_SHORT)
                    .show()
            } catch (e: Exception) {
                Log.e(TAG, "onClickAccountInfo: 계좌정보 클립보드 복사 실패")
            }
        }
    }
}