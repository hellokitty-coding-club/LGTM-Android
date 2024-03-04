package com.lgtm.android.common_ui.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lgtm.android.common_ui.R
import com.lgtm.android.common_ui.theme.LGTMTheme
import com.lgtm.android.common_ui.util.throttleClickable

@Composable
fun MenuButton(
    modifier: Modifier = Modifier,
    dropDownContent: @Composable (expanded: Boolean, onDismissRequest: () -> Unit) -> Unit
) {
    var dropDownExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .wrapContentSize()
            .border(
                width = 1.dp,
                color = if (!dropDownExpanded) LGTMTheme.colors.gray_3 else LGTMTheme.colors.black,
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                color = if (!dropDownExpanded) LGTMTheme.colors.white else LGTMTheme.colors.black,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(3.dp)
            .throttleClickable(enabled = true) {
                dropDownExpanded = !dropDownExpanded
            }
    ) {
        Image(
            painter = if (!dropDownExpanded) painterResource(id = R.drawable.ic_menu_black) else painterResource(id = R.drawable.ic_menu_green),
            contentDescription = null
        )
        dropDownContent(dropDownExpanded) {
            dropDownExpanded = false
        }
    }
}

@Preview
@Composable
fun MenuButtonPreview() {
    LGTMTheme {
        MenuButton {_, _ -> }
    }
}