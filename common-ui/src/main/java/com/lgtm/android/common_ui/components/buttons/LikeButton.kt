package com.lgtm.android.common_ui.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lgtm.android.common_ui.R
import com.lgtm.android.common_ui.theme.LGTMTheme
import com.lgtm.android.common_ui.util.throttleClickable

@Composable
fun LikeButton(
    modifier: Modifier = Modifier,
    likeNum: String,
    isLiked: Boolean,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(
                width = 1.dp,
                color = LGTMTheme.colors.gray_2,
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                color = LGTMTheme.colors.white,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(
                horizontal = 6.dp,
                vertical = 9.dp
            )
            .throttleClickable(
                enabled = true,
                onClick = onClick
            )
    ) {
        
        Text(
            modifier = Modifier
                .requiredWidth(30.dp),
            text = likeNum,
            textAlign = TextAlign.Center,
            style = LGTMTheme.typography.body3R
        )

        Image(
            modifier = Modifier.padding(start = 2.dp),
            imageVector = if (isLiked) ImageVector.vectorResource(id = R.drawable.ic_like_selected) else ImageVector.vectorResource(id = R.drawable.ic_like_unselected),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun LikeButtonPreview() {
    LGTMTheme {
        LikeButton(
            likeNum = "22",
            isLiked = true,
            onClick = {}
        )
    }
}