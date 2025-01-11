package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.launch

object EventListener {
    private val _eventFlow = MutableSharedFlow<MyEvent>()
    val eventFlow: SharedFlow<MyEvent> = _eventFlow

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun sendEvent(event: MyEvent){
        coroutineScope.launch {
            _eventFlow.emit(event)
        }
    }

    inline fun <reified T : MyEvent> collectOneEvent(
        scope: CoroutineScope,
        crossinline function: (T) -> Unit
    ) {
        scope.launch {
            eventFlow.filterIsInstance<T>().collect { event ->
                function.invoke(event)
            }
        }
    }

    inline fun collectAllEvents(
        scope: CoroutineScope,
        crossinline function: (myEvent : MyEvent) -> Unit
    ) {
        scope.launch {
            eventFlow.filterIsInstance<MyEvent>().collect { event ->
                function.invoke(event)
            }
        }
    }
}