package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event.EventListener
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event.MyEvent

abstract class BaseViewModel : ViewModel() {
    init {
        EventListener.collectAllEvents(viewModelScope) { event ->
            observeMyEvents(event)
        }
    }

    protected fun sendEvent(event: MyEvent) {
        EventListener.sendEvent(event)
    }

    protected open fun observeMyEvents(event: MyEvent) {}
}