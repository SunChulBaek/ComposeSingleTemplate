package com.example.composetemplate.ui.home.tab2

import android.util.Base64
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composetemplate.event.NavItemReselectEvent
import com.example.composetemplate.ui.common.MyWebView
import com.example.composetemplate.ui.common.PText
import com.example.composetemplate.util.EventBus

@Composable
fun Tab2Screen(
    route: String,
    viewModel: Tab2ViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit,
    navigate: (String) -> Unit
) {
    val uiState = viewModel.uiState
    val reselectEvent by EventBus.subscribe<NavItemReselectEvent>().collectAsState(NavItemReselectEvent())

    LaunchedEffect(true) {
        viewModel.init()
    }

    LaunchedEffect(reselectEvent) {
        if (reselectEvent.route == route) {
            // TODO : 탭 재선택 시 동작 (ex. 최상단 스크롤)
            showSnackbar("$route 리셀렉")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = {
                val encoded = Base64.encodeToString("https://www.google.com".toByteArray(), Base64.DEFAULT)
                navigate("webview/$encoded")
            }
        ) {
            PText(
                text = uiState.text,
            )
        }
    }
}