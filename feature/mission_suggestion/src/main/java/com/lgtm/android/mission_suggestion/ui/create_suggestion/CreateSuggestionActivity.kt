package com.lgtm.android.mission_suggestion.ui.create_suggestion

import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.mission_suggestion.R
import com.lgtm.android.mission_suggestion.databinding.ActivityCreateSuggestionBinding
import com.lgtm.android.mission_suggestion.ui.create_suggestion.presentation.CreateSuggestionScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateSuggestionActivity: BaseActivity<ActivityCreateSuggestionBinding>(R.layout.activity_create_suggestion) {
    private val createSuggestionViewModel by viewModels<CreateSuggestionViewModel>()
    override fun initializeViewModel() {
        viewModel = createSuggestionViewModel
    }

    override fun onResume() {
        super.onResume()
        setComposableContent()
    }
    
    private fun setComposableContent() {
        binding.composeView.setContent {
            MaterialTheme {
                CreateSuggestionScreen {
                    setBackButtonClick()
                }
            }
        }
    }

    private fun setBackButtonClick() {
        finish()
    }

}