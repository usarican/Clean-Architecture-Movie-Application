package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType


sealed class MyEvent {
    data class SeeAllClickEvent(val movieType: MovieType) : MyEvent()
}