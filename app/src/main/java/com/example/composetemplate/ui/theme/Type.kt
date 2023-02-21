package com.example.composetemplate.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.composetemplate.R

val pretendard = FontFamily(
    Font(R.font.pretendard_black, FontWeight.W900),
    Font(R.font.pretendard_extra_bold, FontWeight.W800),
    Font(R.font.pretendard_bold, FontWeight.W700),
    Font(R.font.pretendard_semi_bold, FontWeight.W600),
    Font(R.font.pretendard_medium, FontWeight.W500),
    Font(R.font.pretendard_regular, FontWeight.W400),
    Font(R.font.pretendard_light, FontWeight.W300),
    Font(R.font.pretendard_extra_light, FontWeight.W200),
    Font(R.font.pretendard_thin, FontWeight.W100),
)

// 참고 : https://developer.android.com/jetpack/compose/designsystems/material3#typography
// Material Design 3는 typography에 defaultFontFamily를 설정 할 수 없음. 각 TextStyle에 설정해야함.
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)