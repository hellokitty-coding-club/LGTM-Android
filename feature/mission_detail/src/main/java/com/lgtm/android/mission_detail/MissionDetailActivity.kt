package com.lgtm.android.mission_detail

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.mission_detail.databinding.ActivityMissionDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates


@AndroidEntryPoint
class MissionDetailActivity :
    BaseActivity<ActivityMissionDetailBinding>(R.layout.activity_mission_detail) {
    private val missionDetailViewModel by viewModels<MissionDetailViewModel>()
    private var missionId by Delegates.notNull<Int>()
    override fun initializeViewModel() {
        viewModel = missionDetailViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getExtraData()
        setupViewModel()
        setBackButtonClickListener()
        setShareButtonClickListener()
        setMenuButtonClickListener()
        setOnClickBottomButton()
        observeMissionDetailUiState()
    }

    override fun onStart() {
        super.onStart()
        getMissionDetail()
    }

    private fun observeMissionDetailUiState() {
        missionDetailViewModel.missionDetailUiState.observe(this) {
            missionDetailViewModel.setRecommendToEmptyVisibility()
            missionDetailViewModel.setNotRecommendToEmptyVisibility()
        }
    }

    private fun setupViewModel() {
        binding.viewModel = missionDetailViewModel
    }

    private fun getExtraData() {
        missionId = intent.getIntExtra(MISSION_ID, defaultValue)
    }

    private fun setBackButtonClickListener() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun setShareButtonClickListener() {
        binding.ivShare.setOnClickListener {
            val message = missionDetailViewModel.getMissionInfoMessage()
            val intentShare = Intent(Intent.ACTION_SEND)
            intentShare.putExtra(Intent.EXTRA_TEXT, intent.getStringExtra(message))
            intentShare.type = "text/plain"
            startActivity(Intent.createChooser(intentShare, "앱을 선택해 주세요."))
        }
    }

    private fun setMenuButtonClickListener() {
        binding.ivMenu.setOnClickListener {
            showMenuDialog()
        }
    }

    private fun showMenuDialog() {
        // todo show menu dialog
    }

    private fun setOnClickBottomButton() {
        binding.btnMissionDetail.setOnClickListener {
            // todo set on click bottom button
        }
    }


    private fun getMissionDetail() {
        missionDetailViewModel.getMissionDetail(missionId)
    }

    companion object {
        const val MISSION_ID = "mission_id"
        const val defaultValue = -1
    }
}