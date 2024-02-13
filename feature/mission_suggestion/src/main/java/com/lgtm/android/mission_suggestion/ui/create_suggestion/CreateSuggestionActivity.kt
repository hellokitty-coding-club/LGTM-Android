package com.lgtm.android.mission_suggestion.ui.create_suggestion

import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import com.lgtm.android.common_ui.R
import com.lgtm.android.common_ui.base.BaseActivity
import com.lgtm.android.common_ui.ui.LgtmConfirmationDialog
import com.lgtm.android.mission_suggestion.databinding.ActivityCreateSuggestionBinding
import com.lgtm.android.mission_suggestion.ui.create_suggestion.presentation.CreateSuggestionScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateSuggestionActivity: BaseActivity<ActivityCreateSuggestionBinding>(com.lgtm.android.mission_suggestion.R.layout.activity_create_suggestion) {
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
                CreateSuggestionScreen(
                    onBackButtonClick = ::setBackButtonClick,
                    showStopCreationDialog = ::showStopCreationDialog
                )
            }
        }
    }

    private fun showStopCreationDialog() {
        val dialog = LgtmConfirmationDialog(
            title = getString(R.string.want_to_stop_creating_suggestion),
            description = getString(R.string.content_will_not_be_saved),
            doAfterConfirm = ::setBackButtonClick,
            confirmBtnBackground = LgtmConfirmationDialog.ConfirmButtonBackground.GRAY
        )
        dialog.show(supportFragmentManager, "stop_creation_dialog")
    }

    private fun setBackButtonClick() {
        finish()
    }
}