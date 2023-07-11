package com.lgtm.android.auth.ui.github

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lgtm.android.auth.BuildConfig.LGTM_GITHUB_LOGIN_URL
import com.lgtm.android.auth.databinding.BottomSheetGithubBinding
import com.lgtm.android.auth.ui.OnLoginSuccess


class GithubBottomSheet constructor(private val loginSuccessListener: OnLoginSuccess) :
    BottomSheetDialogFragment() {
    private var _binding: BottomSheetGithubBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetGithubBinding.inflate(layoutInflater, container, false)
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
            (resources.displayMetrics.heightPixels * 0.93).toInt()
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
