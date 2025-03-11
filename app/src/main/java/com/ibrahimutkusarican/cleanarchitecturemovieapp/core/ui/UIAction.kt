package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui

interface UIAction <T: Any> {
    fun action(): T
}