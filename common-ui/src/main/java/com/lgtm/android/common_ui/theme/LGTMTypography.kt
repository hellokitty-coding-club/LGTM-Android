package com.lgtm.android.common_ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography.heading1B: TextStyle
    @Composable get() = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 44.8.sp
    )

val Typography.heading1M: TextStyle
    @Composable get() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 44.8.sp
    )

val Typography.heading2B: TextStyle
    @Composable get() = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 39.2.sp
    )

val Typography.heading2M: TextStyle
    @Composable get() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 39.2.sp
    )

val Typography.heading3B: TextStyle
    @Composable get() = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 33.6.sp
    )

val Typography.heading3M: TextStyle
    @Composable get() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 33.6.sp
    )

val Typography.heading4B: TextStyle
    @Composable get() = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp
    )

val Typography.heading4M: TextStyle
    @Composable get() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 28.sp
    )

val Typography.body1B: TextStyle
    @Composable get() = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight =22.4.sp
    )

val Typography.body1M: TextStyle
    @Composable get() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.4.sp
    )

val Typography.body2: TextStyle
    @Composable get() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 21.sp
    )

val Typography.body3M: TextStyle
    @Composable get() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp
    )

val Typography.body3R: TextStyle
    @Composable get() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp
    )

val Typography.description: TextStyle
    @Composable get() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp
    )