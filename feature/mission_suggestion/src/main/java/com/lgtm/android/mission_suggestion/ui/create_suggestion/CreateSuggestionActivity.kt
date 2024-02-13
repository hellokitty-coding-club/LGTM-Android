package com.lgtm.android.mission_suggestion.ui.create_suggestion

import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.lgtm.android.common_ui.base.BaseComposeActivity
import com.lgtm.android.mission_suggestion.ui.create_suggestion.presentation.CreateSuggestionScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateSuggestionActivity: BaseComposeActivity() {
    private val createSuggestionViewModel by viewModels<CreateSuggestionViewModel>()
    override fun initializeViewModel() {
        viewModel = createSuggestionViewModel
    }

    @Composable
    override fun Content() {
        MaterialTheme {
            CreateSuggestionScreen {
                setBackButtonClick()
            }
        }
    }

    private fun setBackButtonClick() {
        finish()
    }
}