package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.presentation

sealed class MyListUiAction {
    data class MovieClickAction(val movieId : Int) : MyListUiAction()
    data class MovieDeleteAction(val data: MyListViewModel.DeleteMovieData) : MyListUiAction()
    data object UndoAction : MyListUiAction()
    data object SnackBarDeleteAction : MyListUiAction()
}