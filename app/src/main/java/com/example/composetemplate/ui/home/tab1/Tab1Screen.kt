package com.example.composetemplate.ui.home.tab1

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.example.composetemplate.data.model.Photo
import com.example.composetemplate.event.NavItemReselectEvent
import com.example.composetemplate.ui.common.ErrorScreen
import com.example.composetemplate.ui.common.LoadingScreen
import com.example.composetemplate.ui.common.PText
import com.example.composetemplate.util.EventBus

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Tab1Screen(
    route: String,
    viewModel: Tab1ViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit,
    navigate: (String) -> Unit
) {
    val uiState = viewModel.uiState
    val listState = rememberLazyListState()
    val reselectEvent by EventBus.subscribe<NavItemReselectEvent>().collectAsState(NavItemReselectEvent())

    LaunchedEffect(true) {
        viewModel.init()
    }

    LaunchedEffect(reselectEvent) {
        if (reselectEvent.route == route) {
            // TODO : 탭 재선택 시 동작 (ex. 최상단 스크롤)
            listState.animateScrollToItem(0)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (uiState.isLoading) {
            LoadingScreen()
        } else if (uiState.isError) {
            ErrorScreen()
        } else {
            val refreshing by remember { mutableStateOf(false) }
            val pullRefreshState = rememberPullRefreshState(
                refreshing = refreshing,
                onRefresh = { viewModel.init(forced = true) })

            Box(Modifier.pullRefresh(pullRefreshState)) {
                LazyColumn(modifier = Modifier.fillMaxSize(), state = listState) {
                    items(
                        count = uiState.photos.size,
                        key = { index -> uiState.photos[index].id },
                        itemContent = { index ->
                            photoItem(uiState.photos[index]) {
                                showSnackbar("$index 번 째 아이템 클릭")
                                val encoded = android.util.Base64.encodeToString(uiState.photos[index].url.toByteArray(), android.util.Base64.DEFAULT)
                                navigate("photo/${uiState.photos[index].title}/$encoded")
                            }
                        }
                    )
                }
                PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
            }
        }
    }
}

@Composable
fun photoItem(item: Photo, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .fillMaxWidth()
            .height(100.dp)
            .clickable { onClick.invoke() }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            SubcomposeAsyncImage(
                modifier = Modifier.size(100.dp),
                model = item.thumbnailUrl,
                loading = {
                    CircularProgressIndicator(modifier = Modifier.padding(25.dp))
                },
                contentDescription = "thumbnail"
            )
            PText(
                modifier = Modifier
                    .padding(start = 100.dp)
                    .padding(10.dp),
                text = item.title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}