package com.lgtm.android.profile

import android.os.Bundle
import androidx.activity.viewModels
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.profile.adapter.ProfileAdapter
import com.lgtm.android.profile.databinding.ActivityProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint

class ProfileActivity : BaseActivity<ActivityProfileBinding>(R.layout.activity_profile) {
    private val profileViewModel by viewModels<ProfileViewModel>()
    private lateinit var profileAdapter: ProfileAdapter
    private var memberId by Delegates.notNull<Int>()

    override fun initializeViewModel() {
        viewModel = profileViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getExtraData()
        setUserId()
        initAdapter()
        submitDataWhenDataChanged()
    }

    override fun onResume() {
        super.onResume()
        profileViewModel.fetchProfileInfo()
    }

    private fun getExtraData() {
        memberId = intent.getIntExtra(EXTRA_USER_ID, -1)
    }

    private fun setUserId() {
        profileViewModel.setUserId(memberId)
    }

    private fun initAdapter() {
        profileAdapter = ProfileAdapter(::moveToMissionDetail)
        binding.rvProfile.adapter = profileAdapter
    }

    private fun moveToMissionDetail(missionId: Int) {
        lgtmNavigator.navigateToMissionDetail(this, missionId)
    }

    private fun submitDataWhenDataChanged() {
        profileViewModel.profileInfo.observe(this) {
            profileAdapter.submitList(it)
        }
    }


    companion object {
        const val EXTRA_USER_ID = "EXTRA_USER_ID"
    }
}