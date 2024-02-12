package com.lgtm.android.mission_suggestion.ui.create_suggestion.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import com.lgtm.android.common_ui.R
import com.lgtm.android.common_ui.components.buttons.BackButton
import com.lgtm.android.common_ui.constant.InfoType
import com.lgtm.android.common_ui.model.EditTextData
import com.lgtm.android.common_ui.model.NoLimitEditTextData
import com.lgtm.android.common_ui.theme.body1M
import com.lgtm.android.common_ui.theme.body2
import com.lgtm.android.common_ui.theme.heading4B
import com.lgtm.android.common_ui.ui.LGTMEditText
import com.lgtm.android.common_ui.ui.LGTMNoLimitEditText

@Composable
fun CreateSuggestionScreen() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxHeight()
            .background(color = colorResource(id = R.color.white))
            .imePadding()
    ) {
        val (backButton, suggestionSection, nextButton) = createRefs()

        SuggestionSection(
            modifier = Modifier
                .verticalScroll(rememberScrollState(), reverseScrolling = true)
                .constrainAs(suggestionSection) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        CreateSuggestionNextButton(
            modifier = Modifier
                .constrainAs(nextButton) {
                    bottom.linkTo(parent.bottom, margin = 28.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        CreateSuggestionBackButton(
            modifier = Modifier
                .constrainAs(backButton) {
                    top.linkTo(parent.top, margin = 30.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                }
        )
    }
}

@Composable
fun CreateSuggestionBackButton(modifier: Modifier = Modifier) {
    BackButton(modifier)
}

@Composable
fun CreateSuggestionNextButton(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .background(
                color = colorResource(id = R.color.gray_2),
                shape = RoundedCornerShape(5.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 14.dp),
            text = stringResource(id = R.string.suggestion_next),
            style = Typography.body1M,
            color = colorResource(id = R.color.gray_4)
        )
    }
}

@Composable
fun SuggestionSection(modifier: Modifier = Modifier) {
    Column(
        modifier
            .padding(
                bottom = 193.dp,
                start = 20.dp,
                end = 20.dp
            ),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        SuggestionTitle()
        SuggestionContent()
    }
}

@Composable
fun SuggestionTitle() {
    val lifecycleOwner = LocalLifecycleOwner.current
    val suggestionTitleEditTextData = MutableLiveData(
        EditTextData(
            text = MutableLiveData(""),
            infoStatus = MutableLiveData(InfoType.NONE),
            maxLength = 100,
            hint = "제목을 입력하세요."
        )
    )
    Column(
        modifier = Modifier
            .padding(top = 107.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start,
    ) {
        SubTitleWithDescription(
            subTitle = R.string.mission_suggestion_title,
            description = R.string.mission_suggestion_title_description
        )

        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                LGTMEditText(context).apply {
                    setLifecycleOwner(lifecycleOwner)
                    bindEditTextData(suggestionTitleEditTextData)
                    setMaxLine(1)
                }
            }
        )
    }
}

@Composable
fun SuggestionContent() {
    val lifecycleOwner = LocalLifecycleOwner.current
    val suggestionTitleEditTextData = MutableLiveData(
        NoLimitEditTextData(
            text = MutableLiveData(""),
            infoStatus = MutableLiveData(InfoType.NONE),
            hint = "본문을 입력하세요."
        )
    )
    Column(
        modifier = Modifier
            .padding(top = 42.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start,
    ) {
        SubTitleWithDescription(
            subTitle = R.string.mission_suggestion_content,
            description = R.string.mission_suggestion_content_description
        )

        AndroidView(
            modifier = Modifier
                .fillMaxWidth(),
            factory = { context ->
                LGTMNoLimitEditText(context).apply {
                    setLifecycleOwner(lifecycleOwner)
                    bindEditTextData(suggestionTitleEditTextData)
                    setMaxLine(5)
                }
            }
        )
    }
}

@Composable
fun SubTitleWithDescription(
    subTitle: Int,
    description: Int
) {
    Text(
        modifier = Modifier.padding(bottom = 10.dp),
        text = stringResource(id = subTitle),
        style = Typography.heading4B,
        color = colorResource(id = R.color.black)
    )

    Text(
        modifier = Modifier.padding(bottom = 14.dp),
        text = stringResource(id = description),
        style = Typography.body2,
        color = colorResource(id = R.color.gray_6)
    )
}