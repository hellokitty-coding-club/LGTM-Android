package com.lgtm.android.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.PopupMenu
import com.lgtm.android.common_ui.R.dimen
import com.lgtm.android.common_ui.R.id
import com.lgtm.android.common_ui.R.style
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.common_ui.util.ItemDecorationUtil
import com.lgtm.android.common_ui.util.getDrawableCompat
import com.lgtm.android.profile.adapter.ProfileAdapter
import com.lgtm.android.profile.databinding.ActivityProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ProfileActivity : BaseActivity<ActivityProfileBinding>(R.layout.activity_profile),
    PopupMenu.OnMenuItemClickListener, PopupMenu.OnDismissListener {
    private val profileViewModel by viewModels<ProfileViewModel>()
    private lateinit var profileAdapter: ProfileAdapter
    private var memberId: Int? = null
    override fun initializeViewModel() {
        viewModel = profileViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getExtraData()
        setUserId()
        initAdapter()
        submitDataWhenDataChanged()
        setBackButtonClickListener()
        setShareButtonClickListener()
        setMenuButtonClickListener()
    }

    override fun onResume() {
        super.onResume()
        profileViewModel.fetchProfileInfo()
    }

    private fun getExtraData() {
        memberId = intent.getIntExtra(EXTRA_USER_ID, NO_USER_ID)
    }

    private fun setUserId() {
        if (isUserIdAvailable()) profileViewModel.setUserId(memberId)
    }

    private fun isUserIdAvailable() = (memberId != NO_USER_ID)

    private fun initAdapter() {
        profileAdapter = ProfileAdapter(::moveToMissionDetail, ::openGithubProfile)
        binding.rvProfile.adapter = profileAdapter
    }

    private fun moveToMissionDetail(missionId: Int) {
        lgtmNavigator.navigateToMissionDetail(this, missionId)
    }

    private fun openGithubProfile() {
        val url = profileViewModel.getGithubProfileUrl()
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun submitDataWhenDataChanged() {
        profileViewModel.profileInfo.observe(this) {
            profileAdapter.submitList(it)
            addItemDecoration()
        }
    }

    private fun addItemDecoration() {
        val itemDecoration = ItemDecorationUtil.BottomItemDecoration(
            padding = dimen.profile_first_mission_top_margin,
            index = profileViewModel.getFirstMissionIdx()
        )
        binding.rvProfile.addItemDecoration(itemDecoration)
    }

    private fun setBackButtonClickListener() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun setShareButtonClickListener() {
        binding.ivShare.setOnClickListener {
            // 현재 visibility false
            // 추후 deeplink 적용 후 공유하기 기능 추가
        }
    }

    private fun setMenuButtonClickListener() {
        binding.ivMenu.setOnClickListener {
            showMenu(it)
            setDartMenuIcon()
        }
    }

    private fun showMenu(v: View) {
        PopupMenu(
            this, v, Gravity.START, 0, style.PopupMenu
        ).apply {
            val menu = if (profileViewModel.isMyProfile())
                com.lgtm.android.common_ui.R.menu.menu_edit else com.lgtm.android.common_ui.R.menu.menu_report
            setOnMenuItemClickListener(this@ProfileActivity)
            setOnDismissListener(this@ProfileActivity)
            inflate(menu)
            show()
        }
    }

    private fun setDartMenuIcon() {
        with(binding.ivMenu) {
            background =
                this.getDrawableCompat(com.lgtm.android.common_ui.R.drawable.rectangle_black_radius_10)
            setImageDrawable(this.getDrawableCompat(com.lgtm.android.common_ui.R.drawable.ic_menu_green))
        }
    }


    companion object {
        const val EXTRA_USER_ID = "EXTRA_USER_ID"
        const val NO_USER_ID = -1
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            id.report -> {
                makeToastServiceUnderPreparation()
                true
            }

            id.edit_profile -> {
                makeToastServiceUnderPreparation()
                true
            }

            else -> false
        }
    }

    private fun makeToastServiceUnderPreparation() {
        Toast.makeText(this, com.lgtm.android.common_ui.R.string.service_under_preparation, Toast.LENGTH_SHORT).show()
    }

    override fun onDismiss(menu: PopupMenu?) {
        setLightMenuIcon()
    }

    private fun setLightMenuIcon() {
        with(binding.ivMenu) {
            background =
                this.getDrawableCompat(com.lgtm.android.common_ui.R.drawable.rectangle_white_stroke_gray_3_radius_10)
            setImageDrawable(this.getDrawableCompat(com.lgtm.android.common_ui.R.drawable.ic_menu_black))
        }
    }

}