package com.example.composetemplate.ui.home.tab4

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composetemplate.BuildConfig
import com.example.composetemplate.event.NavItemReselectEvent
import com.example.composetemplate.util.EventBus

@Composable
fun Tab4Screen(
    route: String,
    viewModel: Tab4ViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit,
    navigate: (String, Any?) -> Unit
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
        Column(Modifier.align(Alignment.Center)) {
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = { showSnackbar("$route 클릭") }
            ) {
                Text(text = uiState.text,)
            }
            Text(text = "App ID : ${BuildConfig.APPLICATION_ID}")
            Text(text = "Version Code = ${BuildConfig.VERSION_CODE}")
            Text(text = "Version Name = ${BuildConfig.VERSION_NAME}")
        }
    }
}