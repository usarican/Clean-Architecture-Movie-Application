package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action

interface UIAction <T: Any> {
    fun action(): T
}