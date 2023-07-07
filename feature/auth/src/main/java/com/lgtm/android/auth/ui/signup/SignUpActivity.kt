package com.lgtm.android.auth.ui.signup

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.ActivitySignUpBinding
import com.lgtm.android.auth.ui.SignInActivity.Companion.GITHUB_ID
import com.lgtm.android.common_ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getExtraData()
    }

    private fun getExtraData() {
        val memberData = intent.getStringExtra(GITHUB_ID) ?: return
        Log.d(TAG, "getExtraData: $memberData")
        //authViewModel.get
    }
}