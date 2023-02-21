package com.example.composetemplate.ui.common

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.composetemplate.ui.theme.pretendard

// Pretendard 폰트가 적용된 Text
@Composable
fun PText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    color: Color = LocalTextStyle.current.color
) = Text(
    text = text,
    modifier = modifier,
    color = color,
    style = style,
    fontFamily = pretendard
)