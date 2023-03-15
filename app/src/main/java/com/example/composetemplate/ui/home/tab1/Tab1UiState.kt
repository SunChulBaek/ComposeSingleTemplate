package com.example.composetemplate.ui.home.tab1

import com.example.composetemplate.data.model.Photo

sealed interface Tab1UiState {
    data class Success(
        val photos: List<Photo> = listOf()
    ) : Tab1UiState
    object Loading : Tab1UiState
    object Error: Tab1UiState
}