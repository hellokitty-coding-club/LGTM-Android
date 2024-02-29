package com.lgtm.android.common_ui.components.texts

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lgtm.android.common_ui.theme.LGTMTheme

@Composable
fun DateTimeText(
    date: String,
    time: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(end = 3.dp),
            text = date,
            style = LGTMTheme.typography.description,
            color = LGTMTheme.colors.gray_5,
        )

        Divider(
            color = LGTMTheme.colors.gray_3,
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight()
                .padding(
                    vertical = 2.dp
                )
        )

        Text(
            modifier = Modifier.padding(start = 3.dp),
            text = time,
            style = LGTMTheme.typography.description,
            color = LGTMTheme.colors.gray_5
        )
    }
}

@Preview
@Composable
fun DateTimePreview() {
    LGTMTheme {
        DateTimeText(
            date = "2021.09.01",
            time = "오후 12:00"
        )
    }
}