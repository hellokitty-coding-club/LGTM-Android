package com.lgtm.android.mission_detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.PopupMenu
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.lgtm.android.common_ui.R.id
import com.lgtm.android.common_ui.R.string
import com.lgtm.android.common_ui.R.style
import com.lgtm.android.common_ui.adapter.TechTagAdapter
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.common_ui.ui.LgtmConfirmationDialog
import com.lgtm.android.common_ui.util.NetworkState
import com.lgtm.android.common_ui.util.getDrawableCompat
import com.lgtm.android.mission_detail.databinding.ActivityMissionDetailBinding
import com.lgtm.domain.constants.MissionDetailStatus
import com.lgtm.domain.constants.MissionDetailStatus.JUNIOR_NOT_PARTICIPATE_MISSION_FINISH
import com.lgtm.domain.constants.MissionDetailStatus.JUNIOR_NOT_PARTICIPATE_RECRUITING
import com.lgtm.domain.constants.MissionDetailStatus.JUNIOR_PARTICIPATE_MISSION_FINISH
import com.lgtm.domain.constants.MissionDetailStatus.JUNIOR_PARTICIPATE_RECRUITING
import com.lgtm.domain.constants.MissionDetailStatus.SENIOR_NOT_PARTICIPATE_MISSION_FINISH
import com.lgtm.domain.constants.MissionDetailStatus.SENIOR_NOT_PARTICIPATE_RECRUITING
import com.lgtm.domain.constants.MissionDetailStatus.SENIOR_PARTICIPATE_MISSION_FINISH
import com.lgtm.domain.constants.MissionDetailStatus.SENIOR_PARTICIPATE_RECRUITING
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates


@AndroidEntryPoint
class MissionDetailActivity :
    BaseActivity<ActivityMissionDetailBinding>(R.layout.activity_mission_detail),
    PopupMenu.OnMenuItemClickListener, PopupMenu.OnDismissListener {
    private val missionDetailViewModel by viewModels<MissionDetailViewModel>()
    private var missionId by Delegates.notNull<Int>()
    private lateinit var techTagAdapter: TechTagAdapter
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
        setOnMissionUrlClickListener()
        setOnProfileClickListener()
        observeMissionDetailUiState()
        initAdapter()
        setRecyclerViewLayoutManager()
        observeParticipateMissionUiState()
    }

    override fun onResume() {
        super.onResume()
        getMissionDetail()
    }

    private fun initAdapter() {
        techTagAdapter = TechTagAdapter()
        binding.rvTechTag.adapter = techTagAdapter
    }

    private fun setRecyclerViewLayoutManager() {
        val layoutManager = FlexboxLayoutManager(this).apply {
            flexWrap = FlexWrap.WRAP
        }
        binding.rvTechTag.layoutManager = layoutManager
    }

    private fun observeMissionDetailUiState() {
        missionDetailViewModel.missionDetailUiState.observe(this) {
            missionDetailViewModel.setRecommendToEmptyVisibility()
            missionDetailViewModel.setNotRecommendToEmptyVisibility()
            techTagAdapter.submitList(it.techTagList)
            binding.profileGlance.data = requireNotNull(it.memberProfile)
        }
    }

    private fun setupViewModel() {
        binding.viewModel = missionDetailViewModel
    }

    private fun getExtraData() {
        missionId = intent.getIntExtra(MISSION_ID, defaultValue)
    }

    private fun setOnProfileClickListener() {
        binding.clReviewerInfo.setOnClickListener {
            navigateToProfile()
        }
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
            showMenu(it)
            setDartMenuIcon()
        }
    }

    override fun onDismiss(menu: PopupMenu?) {
        setLightMenuIcon()
    }

    private fun setDartMenuIcon() {
        with(binding.ivMenu) {
            background =
                this.getDrawableCompat(com.lgtm.android.common_ui.R.drawable.rectangle_black_radius_10)
            setImageDrawable(this.getDrawableCompat(com.lgtm.android.common_ui.R.drawable.ic_menu_green))
        }
    }

    private fun setLightMenuIcon() {
        with(binding.ivMenu) {
            background =
                this.getDrawableCompat(com.lgtm.android.common_ui.R.drawable.rectangle_white_stroke_gray_3_radius_10)
            setImageDrawable(this.getDrawableCompat(com.lgtm.android.common_ui.R.drawable.ic_menu_black))
        }
    }


    private fun showMenu(v: View) {
        PopupMenu(
            this, v, Gravity.START, 0, style.PopupMenu
        ).apply {
            val menu = if (missionDetailViewModel.isMyMission())
                com.lgtm.android.common_ui.R.menu.menu_edit_delete else com.lgtm.android.common_ui.R.menu.menu_report
            setOnMenuItemClickListener(this@MissionDetailActivity)
            setOnDismissListener(this@MissionDetailActivity)
            inflate(menu)
            show()
        }
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            id.report -> {
                Toast.makeText(this, string.service_under_preparation, Toast.LENGTH_SHORT).show()
                true
            }

            id.delete_mission -> {
                Toast.makeText(this, string.service_under_preparation, Toast.LENGTH_SHORT).show()
                true
            }

            id.edit_profile -> {
                Toast.makeText(this, string.service_under_preparation, Toast.LENGTH_SHORT).show()
                true
            }

            else -> false
        }
    }

    private fun setOnMissionUrlClickListener() {
        binding.clMissionUrl.setOnClickListener {
            val url: String = missionDetailViewModel.getMissionUrl() ?: return@setOnClickListener
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }


    private fun setOnClickBottomButton() {
        binding.btnMissionDetail.setOnClickListener {
            val missionDetailStatus: MissionDetailStatus =
                missionDetailViewModel.getMissionDetailStatus() ?: return@setOnClickListener
            when (missionDetailStatus) {
                JUNIOR_PARTICIPATE_RECRUITING -> navigateJuniorMyMission()
                JUNIOR_PARTICIPATE_MISSION_FINISH -> navigateJuniorMyMission()
                JUNIOR_NOT_PARTICIPATE_RECRUITING -> showCheckParticipatingDialog()
                JUNIOR_NOT_PARTICIPATE_MISSION_FINISH -> {/* Button Disable */
                }

                SENIOR_PARTICIPATE_RECRUITING -> navigateToDashboard()
                SENIOR_PARTICIPATE_MISSION_FINISH -> navigateToDashboard()
                SENIOR_NOT_PARTICIPATE_RECRUITING -> {/* Button Disable */
                }

                SENIOR_NOT_PARTICIPATE_MISSION_FINISH -> {/* Button Disable */
                }
            }
        }
    }

    private fun navigateToDashboard() {
        lgtmNavigator.navigateToDashboard(this, missionDetailViewModel.getMissionId())
    }

    private fun navigateToProfile() {
        val userId = missionDetailViewModel.getReviewerId() ?: return
        lgtmNavigator.navigateToProfile(this, userId)
    }

    private fun navigateJuniorMyMission() {
        val missionId = missionDetailViewModel.getMissionId()
        lgtmNavigator.navigateToPingPongJunior(this, missionId)
    }

    private fun showCheckParticipatingDialog() {
        val dialog = LgtmConfirmationDialog(
            title = getString(string.do_you_want_to_participate_mission),
            description = getString(string.cannot_cancle_after_participate_mission),
            doAfterConfirm = ::participateMission,
            confirmBtnBackground = LgtmConfirmationDialog.ConfirmButtonBackground.GREEN
        )
        dialog.show(supportFragmentManager, this.javaClass.name)
    }

    private fun participateMission() {
        missionDetailViewModel.participateMission()
    }

    private fun observeParticipateMissionUiState() {
        missionDetailViewModel.participateMissionUiState.observe(this) {
            when (it) {
                is NetworkState.Init -> {
                    // no-op
                }

                is NetworkState.Success -> {
                    navigateJuniorMyMission()
                }

                is NetworkState.Failure -> {
                    Toast.makeText(this, "${it.msg}", Toast.LENGTH_SHORT).show()
                }
            }
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