package com.lgtm.android.common_ui.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lgtm.android.common_ui.R
import com.lgtm.android.common_ui.components.buttons.ConfirmButtonBackgroundColor
import com.lgtm.android.common_ui.components.buttons.DialogButton
import com.lgtm.android.common_ui.theme.body1B
import com.lgtm.android.common_ui.theme.description

@Composable
fun LGTMBottomSheetDialogContent(
    dialogTitle: String,
    dialogDescription: String? = null,
    onClickCancel: () -> Unit,
    onClickConfirm: () -> Unit,
    confirmBtnBackground: ConfirmButtonBackgroundColor
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.white)),
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
        style = Typography.body1B,
        color = colorResource(id = R.color.black)
    )
    description?.let {
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = description,
            style = Typography.description,
            color = colorResource(id = R.color.black)
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
            top = 30.dp,
            bottom = 30.dp
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        DialogButton(
            text = stringResource(id = R.string.no),
            onClick = onClickCancel,
        )
        DialogButton(
            modifier = Modifier.padding(start = 20.dp),
            text = stringResource(id = R.string.yes),
            onClick = onClickConfirm,
            confirmBtnBackground = confirmBtnBackground
        )
    }
}

@Composable
@Preview
fun LGTMBottomSheetDialogContentPreview() {
    LGTMBottomSheetDialogContent(
        dialogTitle = "작성을 중단할까요?",
        dialogDescription = "작성한 내용은 저장되지 않아요.",
        onClickCancel = {},
        onClickConfirm = {},
        confirmBtnBackground = ConfirmButtonBackgroundColor.GREEN
    )
}