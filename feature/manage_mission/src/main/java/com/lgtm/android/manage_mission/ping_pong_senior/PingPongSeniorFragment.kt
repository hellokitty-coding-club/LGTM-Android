package com.lgtm.android.manage_mission.ping_pong_senior

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lgtm.android.common_ui.util.NetworkState
import com.lgtm.android.manage_mission.dashboard.DashboardViewModel
import com.lgtm.android.manage_mission.databinding.FragmentPingPongSeniorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PingPongSeniorFragment(
    private val juniorId: Int,
    private val missionId: Int
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
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = dashboardViewModel

        onCloseClickListener()
        fetchSeniorMissionStatus()
        observeMissionProcessData()
        setBottomSheetBehavior()
    }

    private fun setBottomSheetBehavior(){
        (dialog as BottomSheetDialog).behavior.apply {
            state = BottomSheetBehavior.STATE_EXPANDED
            skipCollapsed = true
        }
    }

    private fun fetchSeniorMissionStatus() {
        dashboardViewModel.fetchSeniorMissionStatus(missionId = missionId, juniorId = juniorId)
    }


    private fun onCloseClickListener() {
        binding.ivClose.setOnClickListener {
            dismiss()
        }
    }

    private fun observeMissionProcessData() {
        dashboardViewModel.pingPongSeniorState.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkState.Init -> {}
                is NetworkState.Success -> setMissionProcessData()
                is NetworkState.Failure -> {
                    Toast.makeText(requireContext(), "${it.msg}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setMissionProcessData() {
        binding.missionStatus.setData(
            role = dashboardViewModel.getRole(),
            missionStatus = dashboardViewModel.getMissionStatus(),
            missionHistory = dashboardViewModel.getMissionProcessInfo()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}