package com.lgtm.android.auth.ui.github

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.lgtm.android.auth.BuildConfig.DEBUG
import com.lgtm.android.auth.BuildConfig.LGTM_BASE_URL_DEBUG
import com.lgtm.android.auth.BuildConfig.LGTM_BASE_URL_RELEASE
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

    override fun setBottomSheetBehavior() {
        super.setBottomSheetBehavior()
        (dialog as BottomSheetDialog).behavior.apply {
            isDraggable = false
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadGithubLoginUsingWebView() {
        binding.webView.apply {
            settings.javaScriptEnabled = true
            webViewClient =
                GithubWebViewClient(this@GithubBottomSheet, loginSuccessListener)
            loadUrl(getGithubLoginUrl())
        }
    }

    private fun getGithubLoginUrl() =
        (if (DEBUG) LGTM_BASE_URL_DEBUG else LGTM_BASE_URL_RELEASE) + "login/getGithubAuthUrl"

}
