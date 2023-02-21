package com.example.composetemplate.ui.home.tab4

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

data class Tab4UiState(
    val text: String
)

@HiltViewModel
class Tab4ViewModel @Inject constructor() : ViewModel() {

    var uiState by mutableStateOf(Tab4UiState(""))
        private set

    fun init() {
        Timber.d("Tab4ViewModel.init()")
        uiState = Tab4UiState("Tab4Screen()")
    }
}