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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lgtm.android.common_ui.R
import com.lgtm.android.common_ui.components.buttons.BackButton
import com.lgtm.android.common_ui.components.buttons.LikeButton
import com.lgtm.android.common_ui.components.buttons.MenuButton
import com.lgtm.android.common_ui.components.texts.DateTimeText
import com.lgtm.android.common_ui.model.SuggestionUI
import com.lgtm.android.common_ui.theme.LGTMTheme
import com.lgtm.android.common_ui.util.UiState
import com.lgtm.android.mission_suggestion.ui.detail.SuggestionDetailViewModel
import com.lgtm.domain.entity.response.SuggestionLikeVO

@Composable
fun SuggestionDetailScreen(
    viewModel: SuggestionDetailViewModel = hiltViewModel(),
    onBackButtonClick: () -> Unit
) {
    val suggestionDetailState by viewModel.detailState.collectAsStateWithLifecycle()
    val likeSuggestionState by viewModel.likeSuggestionState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .background(LGTMTheme.colors.gray_3),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SuggestionDetailContent(
            modifier = Modifier.weight(1f),
            suggestionDetailState = suggestionDetailState,
            onBackButtonClick = onBackButtonClick
        )
        SuggestionLikeSection(
            modifier = Modifier.padding(vertical = 5.dp),
            likeSuggestionState = likeSuggestionState,
            onLikeButtonClick = viewModel::likeSuggestion,
            onLikeButtonCancel = viewModel::cancelLikeSuggestion
        )
    }
}

@Composable
fun SuggestionDetailContent(
    modifier: Modifier = Modifier,
    suggestionDetailState: UiState<SuggestionUI>,
    onBackButtonClick: () -> Unit
) {
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
        SuggestionDetailTopBar(onBackButtonClick)

        when (suggestionDetailState) {
            is UiState.Init -> { /* no-op */ }
            is UiState.Success -> {
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = suggestionDetailState.data.title,
                    style = LGTMTheme.typography.heading3B,
                    color = LGTMTheme.colors.black
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
                    color = LGTMTheme.colors.gray_2,
                    thickness = 1.dp
                )

                Text(
                    modifier = Modifier.padding(
                        top = 12.dp,
                        start = 20.dp,
                        end = 20.dp
                    ),
                    text = suggestionDetailState.data.description,
                    style = LGTMTheme.typography.body2,
                    color = LGTMTheme.colors.black
                )
            }
            else -> { /* no-op */ }
        }
    }
}

@Composable
fun SuggestionLikeSection(
    modifier: Modifier = Modifier,
    likeSuggestionState: UiState<SuggestionLikeVO>,
    onLikeButtonClick: () -> Unit,
    onLikeButtonCancel: () -> Unit
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = LGTMTheme.colors.gray_2,
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
            modifier = Modifier.padding(vertical = 8.dp),
            text = stringResource(id = R.string.want_this_mission),
            style = LGTMTheme.typography.body1B,
            color = LGTMTheme.colors.gray_6
        )
        when (likeSuggestionState) {
            is UiState.Init -> { /* no-op */ }
            is UiState.Success -> {
                LikeButton(
                    likeNum = likeSuggestionState.data.likeNum,
                    isLiked = likeSuggestionState.data.isLiked,
                    onClick = if (likeSuggestionState.data.isLiked) onLikeButtonCancel else onLikeButtonClick
                )
            }
            else -> { /* no-op */ }
        }
    }
}

@Composable
fun SuggestionDetailTopBar(
    onBackButtonClick: () -> Unit
) {
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
        BackButton(onClick = onBackButtonClick)
        MenuButton()
    }
}