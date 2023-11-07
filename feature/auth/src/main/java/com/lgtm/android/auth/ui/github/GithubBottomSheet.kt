package com.lgtm.android.auth.ui.github

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.CookieManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.lgtm.android.auth.BuildConfig.IS_DEV
import com.lgtm.android.auth.BuildConfig.LGTM_BASE_URL_DEBUG
import com.lgtm.android.auth.BuildConfig.LGTM_BASE_URL_RELEASE
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.BottomSheetGithubBinding
import com.lgtm.android.auth.ui.OnLoginSuccess
import com.lgtm.android.common_ui.R.*
import com.lgtm.android.common_ui.base.BaseBottomSheetFragment
import com.lgtm.android.common_ui.util.KeyboardUtil


class GithubBottomSheet constructor(private val loginSuccessListener: OnLoginSuccess) :
    BaseBottomSheetFragment<BottomSheetGithubBinding>(R.layout.bottom_sheet_github) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadGithubLoginUsingWebView()
        setBottomSheetHeight(0.93)
        setSoftKeyboard()
    }

    override fun setBottomSheetBehavior() {
        super.setBottomSheetBehavior()
        (dialog as BottomSheetDialog).behavior.apply {
            isDraggable = false
        }
    }

    private fun setSoftKeyboard() {
        KeyboardUtil().setUpAsSoftKeyboard(binding.webView)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadGithubLoginUsingWebView() {
        clearPreviousLoginCookie()
        binding.webView.apply {
            settings.javaScriptEnabled = true
            webViewClient =
                GithubWebViewClient(this@GithubBottomSheet, loginSuccessListener)
            loadUrl(getGithubLoginUrl())
            Log.d(TAG, "loadGithubLoginUsingWebView: ${getGithubLoginUrl()}")
        }
    }

    private fun clearPreviousLoginCookie() {
        CookieManager.getInstance().removeAllCookies(null)
        CookieManager.getInstance().flush()
    }

    private fun getGithubLoginUrl() =
        (if (IS_DEV) LGTM_BASE_URL_DEBUG else LGTM_BASE_URL_RELEASE) + "login/getGithubAuthUrl"

}
