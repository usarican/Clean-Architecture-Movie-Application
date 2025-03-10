package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event.MyEvent
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.BaseViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.MySnackBarModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.SnackBarType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UIAction
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


    private val _showSnackBar = MutableStateFlow<MySnackBarModel?>(null)
    val showSnackBar: StateFlow<MySnackBarModel?> = _showSnackBar

    fun updateSnackBar(snackBarModel: MySnackBarModel?) {
        _showSnackBar.value = snackBarModel
    }

    private var deleteMovieData: DeleteMovieData? = null

    data class DeleteMovieData(
        val movie: MyListMovieModel,
        val page: MyListUpdatePage
    )


    fun handleUiAction(uiAction: UIAction<Any>) : Any{
        return uiAction.action()
    }

    inner class MovieClickAction(val movieId: Int) : UIAction<Unit> {
        override fun action() {
            sendEvent(MyEvent.MovieClickEvent(movieId))
        }
    }

    inner class MovieDeleteAction(val data: DeleteMovieData?) : UIAction<Unit> {
        override fun action() {
            deleteMovieData = data
            showAreYouSureSnackBar(deleteMovieData)
        }
    }

    inner class UndoAction : UIAction<Unit> {
        override fun action() {
            // TODO: Implement undo behavior
            TODO("Undo action not implemented")
        }
    }

    inner class SnackBarDeleteAction : UIAction<Unit> {
        override fun action() {
            deleteMovie(deleteMovieData)
        }
    }

    inner class GoToExploreAction() : UIAction<Unit> {
        override fun action() {
            sendEvent(MyEvent.GoToExploreEvent)
        }
    }

    inner class InstantMovieDeleteAction(val data: DeleteMovieData) : UIAction<Unit> {
        override fun action() {
            deleteMovie(data)
        }
    }


    private fun showAreYouSureSnackBar(deleteMovieData: DeleteMovieData?) {
        viewModelScope.launch {
            deleteMovieData?.let { data ->
                updateSnackBar(
                    MySnackBarModel(
                        title = stringProvider.getStringFromResource(R.string.are_you_sure),
                        message = stringProvider.getStringFromResource(
                            R.string.my_list_are_you_sure_snack_bar_content,
                            data.movie.title,
                            stringProvider.getStringFromResource(data.page.title)
                        ),
                        type = SnackBarType.WARNING,
                        movieId = data.movie.movieId
                    )
                )
            }
        }
    }

    private fun deleteMovie(deleteMovieData: DeleteMovieData?) {
        deleteMovieData?.let { data ->
            updateMyListMovieUseCase.updateFavoriteMovieFromMyList(data.movie, data.page)
                .doOnSuccess {
                    updateSnackBar(
                        MySnackBarModel(
                            title = stringProvider.getStringFromResource(R.string.delete),
                            message = stringProvider.getStringFromResource(
                                R.string.my_list_delete_snack_bar_content,
                                data.movie.title,
                                stringProvider.getStringFromResource(data.page.title)
                            ),
                            type = SnackBarType.SUCCESS,
                            movieId = data.movie.movieId
                        )
                    )
                }
                .launchIn(viewModelScope)
        }
    }
}