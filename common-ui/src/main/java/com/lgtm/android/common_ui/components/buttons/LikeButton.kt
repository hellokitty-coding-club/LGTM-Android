package com.lgtm.android.common_ui.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lgtm.android.common_ui.R
import com.lgtm.android.common_ui.theme.body3R

@Composable
fun LikeButton(
    likeNum: String,
    isLiked: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.gray_2),
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(
                horizontal = 6.dp,
                vertical = 9.dp
            )
    ) {
        
        Text(
            modifier = Modifier
                .requiredWidth(30.dp),
            text = likeNum,
            textAlign = TextAlign.Center,
            style = Typography.body3R
        )

        Image(
            modifier = Modifier.padding(start = 2.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_like_selected),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun LikeButtonPreview() {
    MaterialTheme {
        LikeButton(
            likeNum = "22",
            isLiked = true
        )
    }
}