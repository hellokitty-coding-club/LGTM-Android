package com.lgtm.android.common_ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import javax.annotation.concurrent.Immutable

@Immutable
data class LGTMColor(
    val yellow: Color,
    val blue: Color,
    val red: Color,
    val green: Color,
    val black: Color,
    val transparent_black: Color,
    val gray_8: Color,
    val gray_7: Color,
    val gray_6: Color,
    val gray_5: Color,
    val gray_4: Color,
    val gray_3: Color,
    val gray_2: Color,
    val gray_1: Color,
    val white: Color,
)

@Immutable
data class LGTMTypography(
    val heading1B: TextStyle,
    val heading1M: TextStyle,
    val heading2B: TextStyle,
    val heading2M: TextStyle,
    val heading3B: TextStyle,
    val heading3M: TextStyle,
    val heading4B: TextStyle,
    val heading4M: TextStyle,
    val body1B: TextStyle,
    val body1M: TextStyle,
    val body2: TextStyle,
    val body3M: TextStyle,
    val body3R: TextStyle,
    val description: TextStyle
)

val LocalColorPalette = staticCompositionLocalOf {
    LGTMColor(
        yellow = Color.Unspecified,
        blue = Color.Unspecified,
        red = Color.Unspecified,
        green = Color.Unspecified,
        black = Color.Unspecified,
        transparent_black = Color.Unspecified,
        gray_8 = Color.Unspecified,
        gray_7 = Color.Unspecified,
        gray_6 = Color.Unspecified,
        gray_5 = Color.Unspecified,
        gray_4 = Color.Unspecified,
        gray_3 = Color.Unspecified,
        gray_2 = Color.Unspecified,
        gray_1 = Color.Unspecified,
        white = Color.Unspecified,
    )
}

val LocalTypography = staticCompositionLocalOf {
    LGTMTypography(
        heading1B = TextStyle.Default,
        heading1M = TextStyle.Default,
        heading2B = TextStyle.Default,
        heading2M = TextStyle.Default,
        heading3B = TextStyle.Default,
        heading3M = TextStyle.Default,
        heading4B = TextStyle.Default,
        heading4M = TextStyle.Default,
        body1B = TextStyle.Default,
        body1M = TextStyle.Default,
        body2 = TextStyle.Default,
        body3M = TextStyle.Default,
        body3R = TextStyle.Default,
        description = TextStyle.Default
    )
}