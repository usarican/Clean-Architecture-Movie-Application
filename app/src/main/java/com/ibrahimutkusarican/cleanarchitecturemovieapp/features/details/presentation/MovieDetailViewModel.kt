package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event.MyEvent
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.BaseViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.MySnackBarModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.SnackBarType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.usecase.GetMovieDetailUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model.MyListUpdatePage
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.usecase.UpdateMyListMovieUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.StringProvider
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val updateMyListMovieUseCase: UpdateMyListMovieUseCase,
    private val stringProvider: StringProvider
) : BaseViewModel() {

    private val _movieDetailModel = MutableStateFlow<MovieDetailModel?>(null)
    val movieDetailModel: StateFlow<MovieDetailModel?> = _movieDetailModel

    private val _uiState = MutableStateFlow<UiState<MovieDetailModel>>(UiState.Loading)
    val uiState: StateFlow<UiState<MovieDetailModel>> = _uiState

    private val _showSnackBar = MutableSharedFlow<MySnackBarModel>()
    val showSnackBar: SharedFlow<MySnackBarModel> = _showSnackBar

    private var movieId : Int? = null

    fun getMovieDetail(movieId: Int) {
       this.movieId = movieId
        getMovieDetailUseCase.getMovieDetail(movieId)
            .doOnSuccess { model ->
                _movieDetailModel.value = model
            }
            .onEach { state -> _uiState.value = state }
            .launchIn(viewModelScope)
    }

    fun handleUiAction(action: DetailUiAction) {
        when (action) {
            DetailUiAction.ErrorRetryAction -> movieId?.let { getMovieDetail(it) }
            is DetailUiAction.RecommendedMovieClickAction -> sendEvent(MyEvent.MovieClickEvent(action.movieId))
            DetailUiAction.OnBackPressClickAction -> sendEvent(MyEvent.OnBackPressed)
            is DetailUiAction.SeeAllClickAction -> sendEvent(MyEvent.SeeAllClickEvent(action.seeAllType))
            is DetailUiAction.DetailButtonClickAction -> {
                when (action.data.type) {
                    MovieDetailActionButtonType.PLAY -> TODO()
                    MovieDetailActionButtonType.SHARE -> TODO()
                    MovieDetailActionButtonType.ADD_FAVORITE -> addMovieFavoriteList(
                        movieDetailModel.value
                    )

                    MovieDetailActionButtonType.ADD_WATCH_LIST -> addMovieWatchList(movieDetailModel.value)
                }
            }
        }
    }

    private fun addMovieFavoriteList(model: MovieDetailModel?) {
        model?.let {
            updateMyListMovieUseCase.updateFavoriteMovieFromDetail(
                movieDetailModel = model, myListUpdatePage = MyListUpdatePage.FAVORITE
            ).doOnSuccess { updateModel ->
                _movieDetailModel.update { updateModel }
                _showSnackBar.emit(
                    MySnackBarModel(
                        title = if (model.movieDetailInfoModel.isFavorite) stringProvider.getStringFromResource(
                            R.string.remove_favorite
                        ) else stringProvider.getStringFromResource(R.string.add_favorite),
                        message = if (model.movieDetailInfoModel.isFavorite) stringProvider.getStringFromResource(
                            R.string.movie_removed_to_favorite, model.movieDetailInfoModel.title
                        ) else stringProvider.getStringFromResource(
                            R.string.movie_added_to_favorite, model.movieDetailInfoModel.title
                        ),
                        type = SnackBarType.SUCCESS
                    )
                )
            }.launchIn(viewModelScope)
        }
    }

    private fun addMovieWatchList(model: MovieDetailModel?) {
        model?.let {
            updateMyListMovieUseCase.updateFavoriteMovieFromDetail(
                movieDetailModel = model, myListUpdatePage = MyListUpdatePage.WATCH_LIST
            ).doOnSuccess { updateModel ->
                _movieDetailModel.update { updateModel }
                _showSnackBar.emit(
                    MySnackBarModel(
                        title = if (model.movieDetailInfoModel.isAddedToWatchList) stringProvider.getStringFromResource(
                            R.string.remove_watch_list
                        ) else stringProvider.getStringFromResource(R.string.add_watch_list),
                        message = if (model.movieDetailInfoModel.isAddedToWatchList) stringProvider.getStringFromResource(
                            R.string.movie_removed_to_watch_list, model.movieDetailInfoModel.title
                        ) else stringProvider.getStringFromResource(
                            R.string.movie_added_to_watch_list, model.movieDetailInfoModel.title
                        ),
                        type = SnackBarType.SUCCESS
                    )
                )
            }.launchIn(viewModelScope)
        }
    }
}


