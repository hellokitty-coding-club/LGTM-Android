package com.lgtm.android.auth.ui.github

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.lgtm.android.auth.ui.OnLoginSuccess
import java.lang.ref.WeakReference


class GithubWebViewClient(
    fragment: GithubBottomSheet,
    private val loginSuccessListener: OnLoginSuccess
) : WebViewClient() {
    private val fragmentReference: WeakReference<GithubBottomSheet> = WeakReference(fragment)

    override fun shouldOverrideUrlLoading(
        view: WebView?,
        request: WebResourceRequest?
    ): Boolean {
        /** Android SDK 26~28 Github App을 이용한 2단계 인증에 문제가있는 관계로,
         * Mbile App을 활용해 2단계 인증을 하는 방법을 SMS로 우회하는 코드 */
        if (request?.url.toString() == TWO_FACTOR_URL_WITH_GITHUB_APP) {
            view?.loadUrl(TWO_FACTOR_URL_WITH_SMS)
            return true
        }
        return false
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        if (isGithubLoginResponseUrl(url)) {
            view?.evaluateJavascript("document.documentElement.outerHTML") { html ->
                val formattedHtml = html.replace("\\u003C", "<")
                onLoginSuccess(formattedHtml)
                fragmentReference.get()?.dismiss()
            }
        }
    }

    private fun isGithubLoginResponseUrl(url: String?): Boolean {
        return url?.contains(GITHUB_LOGIN_RESPONSE_URL) == true
    }

    private fun onLoginSuccess(githubResponse: String) {
        loginSuccessListener.onLoginSuccess(githubResponse)
    }

    companion object {
        const val TWO_FACTOR_URL_WITH_GITHUB_APP =
            "https://github.com/sessions/two-factor/mobile?auto=true"
        const val TWO_FACTOR_URL_WITH_SMS =
            "https://github.com/sessions/two-factor/sms/confirm"
        const val GITHUB_LOGIN_RESPONSE_URL = "login/oauth2/code/github?code="
    }
}