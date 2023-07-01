package com.lgtm.android.auth.sign

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lgtm.android.auth.BuildConfig.GITHUB_LOGIN_URL
import com.lgtm.android.auth.databinding.BottomSheetFragmentGithubLoginBinding


class GithubLoginBottomSheet : BottomSheetDialogFragment() {
    private var _binding: BottomSheetFragmentGithubLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetFragmentGithubLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setBottomSheetHeight()
        loadGithubLoginUsingWebView()
    }

    private fun setBottomSheetHeight() {
        (dialog as BottomSheetDialog).behavior.apply {
            state = BottomSheetBehavior.STATE_EXPANDED
            skipCollapsed = true
            isDraggable = false
        }

        binding.bottomSheet.layoutParams.height =
            (resources.displayMetrics.heightPixels * 0.80).toInt()
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
                Log.d(TAG, "shouldOverrideUrlLoading: ${request?.url.toString()}")
                return false
            }
        }

        webView.apply {
            webView.loadUrl(GITHUB_LOGIN_URL)
        }

//        webView.addJavascriptInterface(WebAppInterface(requireContext()), "Android")
    }
}

///** Instantiate the interface and set the context  */
//class WebAppInterface(private val mContext: Context) {
//
//    @JavascriptInterface
//    fun showToast(toast: String) {
//        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
//    }
//}
