package com.lgtm.android.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.common_ui.util.ItemDecorationUtil
import com.lgtm.android.profile.adapter.ProfileAdapter
import com.lgtm.android.profile.databinding.ActivityProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ProfileActivity : BaseActivity<ActivityProfileBinding>(R.layout.activity_profile) {
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
            // add Item Decoration
            addItemDecoration()
            //             addItemDecoration(
            //                ItemDecorationUtil.BottomItemDecoration(
            //                    padding = com.lgtm.android.common_ui.R.dimen.base_guideline,
            //                    index = profileViewModel.getFirstMissionIdx()
            //                )
            //            )
        }
    }

    private fun addItemDecoration(){
        binding.rvProfile.adapter.let {
            if (it != null) {
                val itemDecoration = ItemDecorationUtil.BottomItemDecoration(
                    padding = com.lgtm.android.common_ui.R.dimen.base_guideline,
                    index = profileViewModel.getFirstMissionIdx()
                )
                binding.rvProfile.addItemDecoration(itemDecoration)
            }
        }
    }


    companion object {
        const val EXTRA_USER_ID = "EXTRA_USER_ID"
        const val NO_USER_ID = -1
    }
}