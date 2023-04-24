package com.example.composetemplate.ui.home.tab1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composetemplate.event.NavItemReselectEvent
import com.example.composetemplate.ui.common.ErrorScreen
import com.example.composetemplate.ui.common.LoadingScreen
import com.example.composetemplate.util.EventBus

@Composable
fun Tab1Screen(
    route: String,
    viewModel: Tab1ViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit,
    navigate: (String, Any?) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    val reselectEvent by EventBus.subscribe<NavItemReselectEvent>().collectAsStateWithLifecycle(
        initialValue = NavItemReselectEvent()
    )

    LaunchedEffect(reselectEvent) {
        if (reselectEvent.route == route) {
            // TODO : 탭 재선택 시 동작 (ex. 최상단 스크롤)
            listState.animateScrollToItem(0)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize().padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        when (uiState) {
            Tab1UiState.Loading -> LoadingScreen()
            Tab1UiState.Error -> ErrorScreen()
            is Tab1UiState.Success -> {
                val photos = (uiState as Tab1UiState.Success).photos
                Box(modifier = Modifier) {
                    LazyColumn(modifier = Modifier.fillMaxSize(), state = listState) {
                        items(
                            count = photos.size,
                            itemContent = { index ->
                                PhotoItem(modifier = Modifier.padding(top = 10.dp), item = photos[index]) {
                                    showSnackbar("$index 번 째 아이템 클릭")
                                    navigate("photo_detail", Pair(photos[index].title, photos[index].url))
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}