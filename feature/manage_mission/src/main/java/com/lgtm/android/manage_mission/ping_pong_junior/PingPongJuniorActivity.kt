package com.lgtm.android.manage_mission.ping_pong_junior

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.lgtm.android.common_ui.adapter.TechTagAdapter
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.common_ui.ui.LgtmConfirmationDialog
import com.lgtm.android.common_ui.util.NetworkState
import com.lgtm.android.manage_mission.R
import com.lgtm.android.manage_mission.databinding.ActivityPingPongJuniorBinding
import com.lgtm.domain.constants.ProcessState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PingPongJuniorActivity :
    BaseActivity<ActivityPingPongJuniorBinding>(R.layout.activity_ping_pong_junior) {
    private lateinit var techTagAdapter: TechTagAdapter
    private val missionId by lazy { intent.getIntExtra(MISSION_ID, defaultValue) }
    private val pingPongJuniorViewModel by viewModels<PingPongJuniorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        initAdapter()
        observeMissionStatusInfo()
        setRecyclerViewLayoutManager()
        setBackButtonClickListener()
        setExtraDataToViewModel()
        observeConfirmJuniorPaymentState()
    }

    private fun setExtraDataToViewModel() {
        pingPongJuniorViewModel.setMissionId(missionId)
    }

    private fun setupViewModel() {
        binding.viewModel = pingPongJuniorViewModel
    }

    private fun initAdapter() {
        techTagAdapter = TechTagAdapter()
        binding.rvTechTag.adapter = techTagAdapter
    }

    private fun observeMissionStatusInfo() {
        pingPongJuniorViewModel.pingPongJuniorUI.observe(this) {
            techTagAdapter.submitList(it.techTagList)
            setMissionProcessData()
        }
    }

    private fun setMissionProcessData() {
        binding.missionStatus.setData(
            role = pingPongJuniorViewModel.getRole(),
            missionStatus = pingPongJuniorViewModel.getMissionStatus(),
            missionHistory = pingPongJuniorViewModel.getMissionHistory()
        )
    }

    private fun setRecyclerViewLayoutManager() {
        val layoutManager = FlexboxLayoutManager(this).apply {
            flexWrap = FlexWrap.WRAP
        }
        binding.rvTechTag.layoutManager = layoutManager
    }


    private fun setBackButtonClickListener() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    override fun initializeViewModel() {
        viewModel = pingPongJuniorViewModel
    }

    override fun onResume() {
        super.onResume()
        fetchJuniorMissionStatus()
        observeFetchMissionStatusState()
    }

    private fun fetchJuniorMissionStatus() {
        pingPongJuniorViewModel.fetchJuniorMissionStatus(missionID = missionId)
    }

    private fun observeFetchMissionStatusState() {
        pingPongJuniorViewModel.fetchMissionStatusState.observe(this) {
            when (it) {
                is NetworkState.Init -> {
                    // do nothing
                }

                is NetworkState.Success -> {
                    setBottomButtonState()
                    setBottomButtonClickListener()
                    setDetailStatus(it.data.processStatus)

                }

                is NetworkState.Failure -> {
                    Toast.makeText(this, "${it.msg}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setDetailStatus(processState: ProcessState) {
        when (processState) {
            ProcessState.WAITING_FOR_PAYMENT -> setDepositInfo()
            ProcessState.PAYMENT_CONFIRMATION -> setDepositInfo()
            ProcessState.MISSION_PROCEEDING -> setSubmittingMission()
            ProcessState.CODE_REVIEW -> setSubmittedMission()
            ProcessState.MISSION_FINISHED -> setSubmittedMission()
            ProcessState.FEEDBACK_REVIEWED -> setSubmittedMission()
        }
    }

    private fun setBottomButtonState() {
        when (pingPongJuniorViewModel.getMissionStatus()) {
            ProcessState.WAITING_FOR_PAYMENT -> binding.btnNext.isEnabled = true
            ProcessState.PAYMENT_CONFIRMATION -> binding.btnNext.isEnabled = false
            ProcessState.MISSION_PROCEEDING -> {
                pingPongJuniorViewModel.isValidUrl.observe(this) {
                    binding.btnNext.isEnabled = it
                }
            }

            ProcessState.CODE_REVIEW -> binding.btnNext.isEnabled = false
            ProcessState.MISSION_FINISHED -> binding.btnNext.isEnabled = true
            ProcessState.FEEDBACK_REVIEWED -> binding.btnNext.isEnabled = true
        }
    }

    private fun setBottomButtonClickListener() {
        binding.btnNext.setOnClickListener {
            when (pingPongJuniorViewModel.getMissionStatus()) {
                ProcessState.WAITING_FOR_PAYMENT -> showCheckDepositDialog()
                ProcessState.PAYMENT_CONFIRMATION -> {/* disable */
                }

                ProcessState.MISSION_PROCEEDING -> {} // showSubmitMissionDialog()
                ProcessState.CODE_REVIEW -> {/* visibility gone */
                }

                ProcessState.MISSION_FINISHED -> {/* 추후 후기 작성하기 기능 추가*/
                }

                ProcessState.FEEDBACK_REVIEWED -> {/* 추후 작성한 후기 보러가기 */
                }
            }
        }
    }

    private fun showCheckDepositDialog() {
        val title = getString(
            com.lgtm.android.common_ui.R.string.deposit_dialog_title,
            pingPongJuniorViewModel.getAccountHolder()
        )
        val description = getString(com.lgtm.android.common_ui.R.string.deposit_dialog_description)
        val dialog = LgtmConfirmationDialog(
            title = title,
            description = description,
            doAfterConfirm = ::postJuniorDepositRequest,
            confirmBtnBackground = LgtmConfirmationDialog.ConfirmButtonBackground.GREEN
        )
        dialog.show(supportFragmentManager, this.javaClass.name)
    }

    private fun postJuniorDepositRequest() {
        pingPongJuniorViewModel.confirmJuniorPayment()
    }

    private fun observeConfirmJuniorPaymentState() {
        pingPongJuniorViewModel.confirmJuniorPaymentState.observe(this) {
            when (it) {
                is NetworkState.Init -> {
                    // do nothing
                }

                is NetworkState.Success -> {
                    Toast.makeText(this, "입금 확인 요청이 전송되었습니다.", Toast.LENGTH_SHORT).show()
                    fetchJuniorMissionStatus()
                }

                is NetworkState.Failure -> {
                    Toast.makeText(this, "${it.msg}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setDepositInfo() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_detail_info, AccountInfoFragment())
            .commit()
    }

    private fun setSubmittingMission() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_detail_info, SubmittingMissionFragment())
            .commit()
    }

    private fun setSubmittedMission() {
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fcv_detail_info, SubmittedMissionFragment()())
//            .commit()
    }

    companion object {
        const val MISSION_ID = "mission_id"
        private const val defaultValue = -1
    }
}