package com.lgtm.android.auth.sign

import android.os.Bundle
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.ActivityAuthBinding
import com.lgtm.android.common_ui.base.BaseActivity

class AuthActivity : BaseActivity<ActivityAuthBinding>(R.layout.activity_auth) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initClickListener()
    }

    private fun initClickListener() {
        binding.clGithub.setOnClickListener {
            GithubLoginBottomSheet().show(supportFragmentManager, "GithubLoginBottomSheet")
            // start GithubActivity
//            startActivity(Intent(this, GithubActivity::class.java))
        }

    }
}