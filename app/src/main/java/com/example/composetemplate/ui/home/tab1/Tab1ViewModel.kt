package com.example.composetemplate.ui.home.tab1

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.composetemplate.data.model.Photo
import com.example.composetemplate.domain.GetPhotosParam
import com.example.composetemplate.domain.GetPhotosUseCase
import com.example.composetemplate.util.onMain
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

data class Tab1UiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val photos: List<Photo> = listOf(),
)

@HiltViewModel
class Tab1ViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {

    var uiState by mutableStateOf(Tab1UiState())
        private set

    fun init(forced: Boolean = false) = onMain {
        if (forced || uiState.photos.isEmpty()) {
            uiState = Tab1UiState(isLoading = true)
            getPhotosUseCase(GetPhotosParam()).collect { result ->
                when {
                    result.isSuccess -> {
                        Timber.d("Get Photo success")
                        uiState = Tab1UiState(photos = result.getOrDefault(listOf()))
                    }
                    result.isFailure -> {
                        Timber.d("Get Photo failed")
                        uiState = Tab1UiState(isError = true)
                    }
                }
            }
        }
    }
}