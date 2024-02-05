package com.lgtm.android.mission_suggestion.ui.detail.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lgtm.android.common_ui.R
import com.lgtm.android.common_ui.components.buttons.BackButton
import com.lgtm.android.common_ui.components.buttons.LikeButton
import com.lgtm.android.common_ui.components.buttons.MenuButton
import com.lgtm.android.common_ui.components.texts.DateTimeText
import com.lgtm.android.common_ui.model.SuggestionUI
import com.lgtm.android.common_ui.theme.body1B
import com.lgtm.android.common_ui.theme.body2
import com.lgtm.android.common_ui.theme.heading3B
import com.lgtm.android.common_ui.util.UiState

@Composable
fun SuggestionDetailScreen(
    suggestionDetailStateHolder: State<UiState<SuggestionUI>>
) {
    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.gray_3)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SuggestionDetailContent(
            modifier = Modifier
                .weight(1f),
            suggestionDetailState = suggestionDetailStateHolder.value
        )
        SuggestionLikeSection(
            modifier = Modifier.padding(vertical = 5.dp),
            suggestionDetailState = suggestionDetailStateHolder.value
        )
    }
}

@Composable
fun SuggestionDetailContent(
    modifier: Modifier = Modifier,
    suggestionDetailState: UiState<SuggestionUI>
) {
    when (suggestionDetailState) {
        is UiState.Init -> {
            /* no-op */
        }
        is UiState.Success -> {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(
                            bottomStart = 20.dp,
                            bottomEnd = 20.dp
                        )
                    )
            ) {
                SuggestionDetailTopBar()

                Text(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = suggestionDetailState.data.title,
                    style = Typography.heading3B,
                    color = Color.Black
                )

                DateTimeText(
                    date = suggestionDetailState.data.date,
                    time = suggestionDetailState.data.time,
                    modifier = Modifier.padding(
                        vertical = 11.dp,
                        horizontal = 20.dp
                    )
                )

                Divider(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    color = colorResource(id = R.color.gray_2),
                    thickness = 1.dp
                )

                Text(
                    modifier = Modifier.padding(
                        top = 12.dp,
                        start = 20.dp,
                        end = 20.dp
                    ),
                    text = suggestionDetailState.data.description,
                    style = Typography.body2,
                    color = Color.Black
                )
            }
        }

        else -> {
            /* no-op */
        }
    }
}

@Composable
fun SuggestionLikeSection(
    modifier: Modifier = Modifier,
    suggestionDetailState: UiState<SuggestionUI>
) {
    when(suggestionDetailState) {
        is UiState.Init -> {
            /* no-op */
        }
        is UiState.Success -> {
            Row (
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(id = R.color.gray_2),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(
                        horizontal = 20.dp,
                        vertical = 14.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = stringResource(id = R.string.want_this_mission),
                    style = Typography.body1B,
                    color = colorResource(id = R.color.gray_6)
                )

                LikeButton(
                    likeNum = suggestionDetailState.data.likeNum,
                    isLiked = suggestionDetailState.data.isLiked
                )
            }
        }

        else -> {
            /* no-op */
        }
    }
}

@Composable
fun SuggestionDetailTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 30.dp,
                horizontal = 20.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BackButton()
        MenuButton()
    }
}