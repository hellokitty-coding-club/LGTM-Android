package com.lgtm.android.auth.sign

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.lgtm.android.auth.BuildConfig
import com.lgtm.android.auth.R
import com.lgtm.android.auth.databinding.ActivityGithubBinding
import com.lgtm.android.common_ui.base.BaseActivity

class GithubActivity : BaseActivity<ActivityGithubBinding>(R.layout.activity_github) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadGithubLoginUsingWebView()
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun loadGithubLoginUsingWebView() {
        val webView = binding.webView
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            setSupportMultipleWindows(true)
        }

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                view?.loadUrl(request?.url.toString())
                return false
            }
        }

        webView.settings.allowContentAccess = true;

        webView.apply {
            webView.loadUrl(BuildConfig.GITHUB_LOGIN_URL)
        }

    }
}