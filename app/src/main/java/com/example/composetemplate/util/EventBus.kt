package com.example.composetemplate.util

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

object EventBus {
    private val _events = MutableSharedFlow<Any>()
    val mutableEvents = _events.asSharedFlow()

    suspend fun publish(event: Any) = _events.emit(event)

    inline fun <reified T> subscribe() = mutableEvents.filter { it is T }.map { it as T }
}