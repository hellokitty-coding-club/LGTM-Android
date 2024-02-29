package com.lgtm.android.common_ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lgtm.android.common_ui.theme.LGTMTheme
import com.lgtm.android.common_ui.util.throttleClickable

@Composable
fun DialogConfirmationButton(
    modifier: Modifier = Modifier,
    text: String,
    confirmBtnBackground: ConfirmButtonBackgroundColor = ConfirmButtonBackgroundColor.GRAY,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = confirmBtnBackground.color,
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                width = if (confirmBtnBackground == ConfirmButtonBackgroundColor.GREEN) 0.dp else 1.dp,
                color = LGTMTheme.colors.gray_3,
                shape = RoundedCornerShape(10.dp)
            )
            .throttleClickable(
                enabled = true,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(
                vertical = 11.dp
            ),
            text = text,
            style = LGTMTheme.typography.body1M,
            color = LGTMTheme.colors.black
        )
    }
}

enum class ConfirmButtonBackgroundColor {
    GREEN,
    GRAY;

    val color: Color
        @Composable
        get() = when (this) {
            GREEN -> LGTMTheme.colors.green
            GRAY -> LGTMTheme.colors.gray_1
        }
}