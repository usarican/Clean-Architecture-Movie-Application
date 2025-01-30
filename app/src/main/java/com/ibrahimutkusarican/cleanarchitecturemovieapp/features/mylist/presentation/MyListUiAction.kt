package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.presentation

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model.MyListMovieModel

sealed class MyListUiAction {
    data class MovieClickAction(val movieId : Int) : MyListUiAction()
    data class MovieDeleteAction(val data : MyListMovieModel) : MyListUiAction()
    data class UndoAction(val data : MyListMovieModel) : MyListUiAction()
}