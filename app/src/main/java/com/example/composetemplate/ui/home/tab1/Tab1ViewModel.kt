package com.example.composetemplate.ui.home.tab1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetemplate.domain.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class Tab1ViewModel @Inject constructor(
    getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {

    val uiState = getPhotosUseCase(null)
        .map { result ->
            result.getOrNull()?.let {
                Tab1UiState.Success(it)
            } ?: Tab1UiState.Error
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Tab1UiState.Loading
        )
}