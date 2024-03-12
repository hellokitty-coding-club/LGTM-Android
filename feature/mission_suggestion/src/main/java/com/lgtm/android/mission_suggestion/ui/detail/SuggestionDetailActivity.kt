package com.lgtm.android.mission_suggestion.ui.detail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.lgtm.android.common_ui.base.BaseComposeActivity
import com.lgtm.android.common_ui.theme.LGTMTheme
import com.lgtm.android.mission_suggestion.ui.detail.presentation.SuggestionDetailScreen
import com.lgtm.android.mission_suggestion.ui.detail.presentation.contract.SuggestionDetailUiEffect
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SuggestionDetailActivity: BaseComposeActivity(){
    private val suggestionDetailViewModel by viewModels<SuggestionDetailViewModel>()
    private val suggestionId by lazy { intent.getIntExtra(SUGGESTION_ID, defaultValue) }

    override fun initializeViewModel() {
        viewModel = suggestionDetailViewModel
    }

    @Composable
    override fun Content() {
        LGTMTheme {
            SuggestionDetailScreen()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeUiEffect()
        fetchSuggestionDetail()
    }
    private fun fetchSuggestionDetail() {
        suggestionDetailViewModel.fetchDetail(suggestionId)
    }

    private fun observeUiEffect() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                suggestionDetailViewModel.detailUiEffect.collect { effect ->
                    when (effect) {
                        is SuggestionDetailUiEffect.SendEmail -> {
                            setReportSuggestionClick()
                        }

                        is SuggestionDetailUiEffect.GoBack -> {
                            goBack()
                        }
                    }
                }
            }
        }
    }

    private fun goBack() {
        finish()
    }

    private fun setReportSuggestionClick() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("hkcc.swm.official@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "[LGTM] 미션 제안 불편사항 신고")
            putExtra(Intent.EXTRA_TEXT, suggestionDetailViewModel.getReportMessage())
        }
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "메일을 보낼 수 없습니다", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val SUGGESTION_ID = "suggestion_id"
        private const val defaultValue = -1
    }
}