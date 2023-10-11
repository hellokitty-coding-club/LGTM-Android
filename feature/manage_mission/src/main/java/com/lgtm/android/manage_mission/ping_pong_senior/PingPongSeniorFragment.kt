package com.lgtm.android.manage_mission.ping_pong_senior

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lgtm.android.manage_mission.dashboard.DashboardViewModel
import com.lgtm.android.manage_mission.databinding.FragmentPingPongSeniorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PingPongSeniorFragment(
    private val juniorId: Int
) : BottomSheetDialogFragment() {

    private var _binding: FragmentPingPongSeniorBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다.")
    private val dashboardViewModel by activityViewModels<DashboardViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPingPongSeniorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: juniorId = $juniorId")
        onCloseClickListener()
    }

    private fun onCloseClickListener() {
        binding.ivClose.setOnClickListener {
            dismiss()
        }
    }

    private fun setMissionProcessData() {
//        binding.missionStatus.setData(
//            role = dashboardViewModel.getRole(),
//            missionStatus = dashboardViewModel.getMissionStatus(),
//            missionHistory = dashboardViewModel.getMissionHistory()
//        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}