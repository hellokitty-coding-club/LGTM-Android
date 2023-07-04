package com.lgtm.android.auth.sign

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lgtm.android.auth.BuildConfig.GITHUB_LOGIN_URL
import com.lgtm.android.auth.databinding.BottomSheetFragmentGithubLoginBinding

class GithubBottomSheet constructor(private val loginSuccessListener: OnLoginSuccess) :
    BottomSheetDialogFragment() {
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
            (resources.displayMetrics.heightPixels * 0.94).toInt()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadGithubLoginUsingWebView() {
        binding.webView.apply {
            settings.javaScriptEnabled = true
            webViewClient =
                GithubWebViewClient(this@GithubBottomSheet, loginSuccessListener)
            loadUrl(GITHUB_LOGIN_URL)
        }
    }
}