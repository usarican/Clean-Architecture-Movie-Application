package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType


sealed class MyEvent {
    data class SeeAllClickEvent(val movieType: MovieType) : MyEvent()
    data object OnBackPressed : MyEvent()
    data class SearchBarClickEvent(val recommendedMovieId : Int?) : MyEvent()
    data class MovieClickEvent(val movieId : Int) : MyEvent()
    data class BannerMovieClickEvent(val clickedMovieIndex : Int) : MyEvent()
    data object RestartApp : MyEvent()
}