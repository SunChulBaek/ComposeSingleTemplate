package com.example.composetemplate.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
   참고 : https://medium.com/@anishakd4/simplify-android-viewmodels-by-using-these-kotlin-extenstions-bc14e7598cf8
 */

inline fun ViewModel.onMain(
    crossinline body: suspend CoroutineScope.() -> Unit
) = viewModelScope.launch() {
    body(this)
}

inline fun ViewModel.onMainImmediate(
    crossinline body: suspend CoroutineScope.() -> Unit
) = viewModelScope.launch(Dispatchers.Main.immediate) {
    body(this)
}

inline fun ViewModel.onIO(
    crossinline body: suspend CoroutineScope.() -> Unit
) = viewModelScope.launch(Dispatchers.IO) {
    body(this)
}

inline fun ViewModel.onDefault(
    crossinline body: suspend CoroutineScope.() -> Unit
) = viewModelScope.launch(Dispatchers.Default) {
    body(this)
}

inline fun ViewModel.onUnconfined(
    crossinline body: suspend CoroutineScope.() -> Unit
) = viewModelScope.launch(Dispatchers.Unconfined) {
    body(this)
}