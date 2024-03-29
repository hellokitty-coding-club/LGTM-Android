package com.lgtm.android.common_ui.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lgtm.android.common_ui.R
import com.lgtm.android.common_ui.components.buttons.ConfirmButtonBackgroundColor
import com.lgtm.android.common_ui.components.buttons.DialogConfirmationButton
import com.lgtm.android.common_ui.theme.LGTMTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LgtmConfirmationDialog(
    bottomSheetState: ModalBottomSheetState,
    dialogTitle: String,
    dialogDescription: String? = null,
    onClickConfirm: () -> Unit,
    confirmBtnBackground: ConfirmButtonBackgroundColor,
    content: @Composable () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(
            topStart = 20.dp,
            topEnd = 20.dp
        ),
        scrimColor = LGTMTheme.colors.transparent_black,
        sheetContent = {
            LgtmConfirmationDialogContent(
                dialogTitle = dialogTitle,
                dialogDescription = dialogDescription,
                onClickCancel = {
                    coroutineScope.launch {
                        bottomSheetState.hide()
                    }
                },
                onClickConfirm = {
                    coroutineScope.launch {
                        bottomSheetState.hide()
                        onClickConfirm()
                    }
                },
                confirmBtnBackground = confirmBtnBackground
            )
        },
        content = content
    )
}

@Composable
fun LgtmConfirmationDialogContent(
    dialogTitle: String,
    dialogDescription: String? = null,
    onClickCancel: () -> Unit,
    onClickConfirm: () -> Unit,
    confirmBtnBackground: ConfirmButtonBackgroundColor
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = LGTMTheme.colors.white),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DialogDescription(dialogTitle, dialogDescription)
        DialogButtons(onClickCancel, onClickConfirm, confirmBtnBackground)
    }
}

@Composable
fun DialogDescription(
    title: String,
    description: String? = null
) {
    Text(
        modifier = Modifier.padding(top = 30.dp),
        text = title,
        style = LGTMTheme.typography.body1B,
        color = LGTMTheme.colors.black
    )
    description?.let {
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = description,
            style = LGTMTheme.typography.description,
            color = LGTMTheme.colors.black
        )
    }
}

@Composable
fun DialogButtons(
    onClickCancel: () -> Unit,
    onClickConfirm: () -> Unit,
    confirmBtnBackground: ConfirmButtonBackgroundColor
) {
    Row(
        modifier = Modifier.padding(
            vertical = 30.dp,
            horizontal = 24.dp
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        DialogConfirmationButton(
            modifier = Modifier.weight(1f),
            text = stringResource(id = R.string.no),
            onClick = onClickCancel,
        )
        Spacer(modifier = Modifier.width(20.dp))
        DialogConfirmationButton(
            modifier = Modifier.weight(1f),
            text = stringResource(id = R.string.yes),
            onClick = onClickConfirm,
            confirmBtnBackground = confirmBtnBackground
        )
    }
}

@Composable
@Preview
fun LGTMBottomSheetDialogContentPreview() {
    LGTMTheme {
        LgtmConfirmationDialogContent(
            dialogTitle = "작성을 중단할까요?",
            dialogDescription = "작성한 내용은 저장되지 않아요.",
            onClickCancel = {},
            onClickConfirm = {},
            confirmBtnBackground = ConfirmButtonBackgroundColor.GREEN
        )
    }
}