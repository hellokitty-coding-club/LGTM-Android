package com.lgtm.android.manage_mission.ping_pong_senior

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lgtm.android.common_ui.R.string
import com.lgtm.android.common_ui.R.string.deposit_confirm_completed
import com.lgtm.android.common_ui.ui.LgtmConfirmationDialog
import com.lgtm.android.common_ui.util.EventObserver
import com.lgtm.android.common_ui.util.NetworkState
import com.lgtm.android.manage_mission.dashboard.DashboardViewModel
import com.lgtm.android.manage_mission.databinding.FragmentPingPongSeniorBinding
import com.lgtm.domain.constants.ProcessState
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
        observeConfirmDepositStatus()
        observeCodeReviewStatus()
    }

    private fun setBottomSheetBehavior() {
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
                is NetworkState.Success -> {
                    setBottomButtonState()
                    setMissionProcessData()
                    setBottomButtonClickListener()
                }

                is NetworkState.Failure -> {
                    Toast.makeText(requireContext(), "${it.msg}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setBottomButtonClickListener() {
        binding.btnNext.setOnClickListener {
            when (dashboardViewModel.getMissionStatus()) {
                ProcessState.WAITING_FOR_PAYMENT -> {/* disable */
                }

                ProcessState.PAYMENT_CONFIRMATION -> showCheckDepositDialog()
                ProcessState.MISSION_PROCEEDING -> {} // showSubmitMissionDialog()
                ProcessState.CODE_REVIEW -> {
                    showCheckReviewDialog()
                }

                ProcessState.MISSION_FINISHED -> {/* 추후 후기 작성하기 기능 추가*/
                }

                ProcessState.FEEDBACK_REVIEWED -> {/* 추후 작성한 후기 보러가기 */
                }
            }
        }
    }

    private fun showCheckDepositDialog() {
        val title = getString(string.confirmed_deposit_data_title)
        val description =
            getString(string.confirmed_deposit_data_description)
        LgtmConfirmationDialog(
            title = title,
            description = description,
            doAfterConfirm = ::confirmDepositCompleted,
            confirmBtnBackground = LgtmConfirmationDialog.ConfirmButtonBackground.GREEN
        ).show(childFragmentManager, this.javaClass.name)
    }

    private fun showCheckReviewDialog() {
        val title = getString(string.completed_review_code_for_sure)
        val description =
            getString(string.confirmed_deposit_data_description)
        LgtmConfirmationDialog(
            title = title,
            description = description,
            doAfterConfirm = ::codeReviewCompleted,
            confirmBtnBackground = LgtmConfirmationDialog.ConfirmButtonBackground.GREEN
        ).show(childFragmentManager, this.javaClass.name)
    }

    private fun confirmDepositCompleted() {
        Log.d(TAG, "confirmDepositCompleted: clicked")
        dashboardViewModel.confirmDepositCompleted(missionId = missionId, juniorId = juniorId)
    }

    private fun codeReviewCompleted() {
        Log.d(TAG, "codeReviewCompleted: clicked")
        dashboardViewModel.codeReviewCompleted(missionId = missionId, juniorId = juniorId)
    }

    private fun observeConfirmDepositStatus() {
        dashboardViewModel.confirmDepositStatus.observe(
            viewLifecycleOwner,
            EventObserver { networkState ->
                when (networkState) {
                    is NetworkState.Init -> return@EventObserver
                    is NetworkState.Success -> {
                        showDepositCompletedToast()
                        fetchSeniorMissionStatus()
                    }

                    is NetworkState.Failure -> {
                        Toast.makeText(requireContext(), "${networkState.msg}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        )
    }

    private fun observeCodeReviewStatus() {
        dashboardViewModel.codeReviewCompletedStatus.observe(
            viewLifecycleOwner,
            EventObserver { networkState ->
                when (networkState) {
                    is NetworkState.Init -> return@EventObserver
                    is NetworkState.Success -> {
                        showCodeReviewCompletedToast()
                        fetchSeniorMissionStatus()
                    }

                    is NetworkState.Failure -> {
                        Toast.makeText(requireContext(), "${networkState.msg}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        )
    }

    private fun showDepositCompletedToast() {
        Toast.makeText(requireContext(), getString(deposit_confirm_completed), Toast.LENGTH_SHORT)
            .show()
    }

    private fun showCodeReviewCompletedToast() {
        Toast.makeText(
            requireContext(), getString(string.code_review_completed), Toast.LENGTH_SHORT
        ).show()
    }

    private fun setMissionProcessData() {
        binding.missionStatus.setData(
            role = dashboardViewModel.getRole(),
            missionStatus = dashboardViewModel.getMissionStatus(),
            missionHistory = dashboardViewModel.getMissionProcessInfo()
        )
    }

    private fun setBottomButtonState() {
        binding.btnNext.isEnabled = when (dashboardViewModel.getMissionStatus()) {
            ProcessState.WAITING_FOR_PAYMENT -> false
            ProcessState.PAYMENT_CONFIRMATION -> true
            ProcessState.MISSION_PROCEEDING -> false
            ProcessState.CODE_REVIEW -> true
            ProcessState.MISSION_FINISHED -> false
            ProcessState.FEEDBACK_REVIEWED -> true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}