package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.presentation

sealed class MyListUiAction {
    data class MovieClickAction(val movieId : Int) : MyListUiAction()
    data class MovieDeleteAction(val data: MyListViewModel.DeleteMovieData) : MyListUiAction()
    data class UndoAction(val data: MyListViewModel.DeleteMovieData) : MyListUiAction()
    data class SnackBarDeleteAction(val deleteCallBack : () -> Unit) : MyListUiAction()
}