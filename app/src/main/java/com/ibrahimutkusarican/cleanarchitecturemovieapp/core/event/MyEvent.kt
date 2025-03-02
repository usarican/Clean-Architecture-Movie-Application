package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data.SeeAllType


sealed class MyEvent {
    data class SeeAllClickEvent(val seeAllType: SeeAllType) : MyEvent()
    data object OnBackPressed : MyEvent()
    data class SearchBarClickEvent(val recommendedMovieId : Int?) : MyEvent()
    data class MovieClickEvent(val movieId : Int) : MyEvent()
    data class BannerMovieClickEvent(val clickedMovieIndex : Int) : MyEvent()
    data object RestartApp : MyEvent()
    data object GoToExploreEvent : MyEvent()
    data class GoToMyListEvent(val page : Int) : MyEvent()
}