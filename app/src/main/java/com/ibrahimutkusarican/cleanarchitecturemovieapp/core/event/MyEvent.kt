package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.MySnackBarModel
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
    data class RotateScreenEvent(val isLandScape : Boolean) : MyEvent()
    data class ChangeBottomNavigationVisibility(val isVisible : Boolean) : MyEvent()
    data class ShowSnackBar(val mySnackBarModel: MySnackBarModel) : MyEvent()
}