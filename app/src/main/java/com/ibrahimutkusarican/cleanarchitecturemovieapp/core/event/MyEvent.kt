package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.BasicMovieModel


sealed class MyEvent {
    data class SeeAllClickEvent(val movieType: MovieType) : MyEvent()
    data object OnBackPressed : MyEvent()
    data class SearchBarClickEvent(val recommendedMovieId : Int?) : MyEvent()
    data class MovieClickEvent(val movieId : Int) : MyEvent()
    data class BannerMovieClickEvent(val bannerMovies : List<BasicMovieModel>, val clickedMovieIndex : Int) : MyEvent()
}