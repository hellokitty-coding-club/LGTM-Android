package com.lgtm.android.common_ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun LGTMTheme(
    content: @Composable () -> Unit
) {
    val lgtmColorPalette = LGTMColor(
        yellow = Color(0xFFfac53d),
        blue = Color(0xFF1d74f5),
        red = Color(0xFFfe504f),
        green = Color(0xFF56ee9b),
        black = Color(0xFF030c1b),
        transparent_black = Color(0x9E030C1B),
        gray_8 = Color(0xFF0b1321),
        gray_7 = Color(0xFF172334),
        gray_6 = Color(0xFF495569),
        gray_5 = Color(0xFF78879f),
        gray_4 = Color(0xFFb1bed2),
        gray_3 = Color(0xFFcfd8e7),
        gray_2 = Color(0xFFe7ecf2),
        gray_1 = Color(0xFFf4f5f9),
        white = Color(0xFFfcfcfc)
    )

    val lgtmTypography = LGTMTypography(
        heading1B = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 44.8.sp
        ),
        heading1M = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            lineHeight = 44.8.sp
        ),
        heading2B = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            lineHeight = 39.2.sp
        ),
        heading2M = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp,
            lineHeight = 39.2.sp
        ),
        heading3B = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 33.6.sp
        ),
        heading3M = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            lineHeight = 33.6.sp
        ),
        heading4B = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            lineHeight = 28.sp
        ),
        heading4M = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            lineHeight = 28.sp
        ),
        body1B = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 22.4.sp
        ),
        body1M = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 22.4.sp
        ),
        body2 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 21.sp
        ),
        body3M = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 18.sp
        ),
        body3R = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 18.sp
        ),
        description = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 18.sp
        )
    )

    CompositionLocalProvider(
        LocalColorPalette provides lgtmColorPalette,
        LocalTypography provides lgtmTypography,
        content = content
    )
}

object LGTMTheme {
    val colors: LGTMColor
        @Composable
        @ReadOnlyComposable
        get() = LocalColorPalette.current
    val typography: LGTMTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}