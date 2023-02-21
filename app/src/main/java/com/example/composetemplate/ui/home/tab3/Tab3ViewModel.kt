package com.example.composetemplate.ui.home.tab3

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

data class Tab3UiState(
    val text: String
)

@HiltViewModel
class Tab3ViewModel @Inject constructor() : ViewModel() {

    var uiState by mutableStateOf(Tab3UiState(""))
        private set

    fun init() {
        Timber.d("Tab3ViewModel.init()")
        uiState = Tab3UiState("Tab3Screen()")
    }
}