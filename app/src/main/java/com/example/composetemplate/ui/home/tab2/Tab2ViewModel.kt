package com.example.composetemplate.ui.home.tab2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

data class Tab2UiState(
    val text: String = ""
)

@HiltViewModel
class Tab2ViewModel @Inject constructor() : ViewModel() {

    var uiState by mutableStateOf(Tab2UiState())
        private set

    fun init() {
        Timber.d("Tab2ViewModel.init()")
        uiState = Tab2UiState("Tab2Screen()")
    }
}