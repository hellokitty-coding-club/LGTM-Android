package com.lgtm.android.profile

import android.os.Bundle
import androidx.activity.viewModels
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.profile.databinding.ActivityProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ProfileActivity : BaseActivity<ActivityProfileBinding>(R.layout.activity_profile) {
    private val profileViewModel by viewModels<ProfileViewModel>()
    override fun initializeViewModel() {
        viewModel = profileViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileViewModel.fetchProfileInfo(115)
    }

    companion object {
        const val EXTRA_USER_ID = "EXTRA_USER_ID"
    }
}