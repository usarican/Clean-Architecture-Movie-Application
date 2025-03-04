package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event.MyEvent
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.BaseViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.MySnackBarModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.SnackBarType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model.MyListMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model.MyListPage
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model.MyListUpdatePage
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.usecase.GetMyListMovieUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.usecase.UpdateMyListMovieUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.StringProvider
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(
    private val getMyListMovieUseCase: GetMyListMovieUseCase,
    private val updateMyListMovieUseCase: UpdateMyListMovieUseCase,
    private val stringProvider: StringProvider
) : BaseViewModel() {

    val favoriteMovies = getMyListMovieUseCase.getMyListMovieUseCase(page = MyListPage.FAVORITE)
        .cachedIn(viewModelScope)
    val watchListMovies = getMyListMovieUseCase.getMyListMovieUseCase(page = MyListPage.WATCH_LIST)
        .cachedIn(viewModelScope)

    private val _deleteStatusFavoriteMovies = MutableStateFlow<Map<Int, Boolean?>>(emptyMap())
    val deleteStatusFavoriteMovies: StateFlow<Map<Int, Boolean?>> = _deleteStatusFavoriteMovies

    fun updateFavoriteDeleteStatus(movieId: Int, status: Boolean?) {
        _deleteStatusFavoriteMovies.value = _deleteStatusFavoriteMovies.value.toMutableMap().apply {
            put(movieId, status)
        }
    }

    private val _deleteStatusWatchListMovies = MutableStateFlow<Map<Int, Boolean?>>(emptyMap())
    val deleteStatusWatchListMovies: StateFlow<Map<Int, Boolean?>> = _deleteStatusWatchListMovies

    fun updateWatchListMovieDeleteStatus(movieId: Int, status: Boolean?) {
        _deleteStatusWatchListMovies.value = _deleteStatusWatchListMovies.value.toMutableMap().apply {
            put(movieId, status)
        }
    }


    private val _showSnackBar = MutableSharedFlow<MySnackBarModel>()
    val showSnackBar: SharedFlow<MySnackBarModel> = _showSnackBar

    private var deleteMovieData: DeleteMovieData? = null

    data class DeleteMovieData(
        val movie: MyListMovieModel,
        val page: MyListUpdatePage
    )


    fun handleUiAction(myListUiAction: MyListUiAction) {
        when (myListUiAction) {
            is MyListUiAction.MovieClickAction -> sendEvent(MyEvent.MovieClickEvent(myListUiAction.movieId))
            is MyListUiAction.MovieDeleteAction ->  {
                deleteMovieData = myListUiAction.data
                showAreYouSureSnackBar(deleteMovieData)
            }

            is MyListUiAction.UndoAction -> TODO()
            is MyListUiAction.SnackBarDeleteAction -> {
                deleteMovie()
            }

            MyListUiAction.GoToExploreAction -> sendEvent(MyEvent.GoToExploreEvent)
        }
    }

    private fun showAreYouSureSnackBar(deleteMovieData: DeleteMovieData?) {
        viewModelScope.launch {
            deleteMovieData?.let {
                _showSnackBar.emit(
                    MySnackBarModel(
                        title = stringProvider.getStringFromResource(R.string.are_you_sure),
                        message = stringProvider.getStringFromResource(
                            R.string.my_list_are_you_sure_snack_bar_content,
                            deleteMovieData.movie.title,
                            stringProvider.getStringFromResource(deleteMovieData.page.title)
                        ),
                        type = SnackBarType.WARNING,
                    )
                )
            }
        }
    }

    private fun deleteMovie() {
        deleteMovieData?.let { data ->
            updateMyListMovieUseCase.updateFavoriteMovieFromMyList(data.movie,data.page)
                .doOnSuccess {
                    _showSnackBar.emit(
                        MySnackBarModel(
                            title = stringProvider.getStringFromResource(R.string.delete),
                            message = stringProvider.getStringFromResource(
                                R.string.my_list_delete_snack_bar_content,
                                data.movie.title,
                                stringProvider.getStringFromResource(data.page.title)
                            ),
                            type = SnackBarType.SUCCESS,
                        )
                    )
                }
                .launchIn(viewModelScope)
        }
    }
}