package com.lgtm.android.auth.ui.github

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.lgtm.android.auth.BuildConfig.LGTM_GITHUB_LOGIN_URL
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.BottomSheetGithubBinding
import com.lgtm.android.auth.ui.OnLoginSuccess
import com.lgtm.android.common_ui.base.BaseBottomSheetFragment


class GithubBottomSheet constructor(private val loginSuccessListener: OnLoginSuccess) :
    BaseBottomSheetFragment<BottomSheetGithubBinding>(R.layout.bottom_sheet_github) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadGithubLoginUsingWebView()
        setBottomSheetHeight(0.93)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadGithubLoginUsingWebView() {
        binding.webView.apply {
            settings.javaScriptEnabled = true
            webViewClient =
                GithubWebViewClient(this@GithubBottomSheet, loginSuccessListener)
            loadUrl(LGTM_GITHUB_LOGIN_URL)
        }
    }

}
