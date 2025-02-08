package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.presentation

import androidx.lifecycle.viewModelScope
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event.MyEvent
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.BaseViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.SnackBarType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.usecase.GetMovieDetailUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.usecase.AddMyListMovieUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.usecase.DeleteMyListMovieUseCase
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
    private val addMyListMovieUseCase: AddMyListMovieUseCase,
    private val deleteMyListMovieUseCase: DeleteMyListMovieUseCase
) : BaseViewModel() {

    private val _movieDetailModel = MutableStateFlow<MovieDetailModel?>(null)
    val movieDetailModel: StateFlow<MovieDetailModel?> = _movieDetailModel

    private val _uiState = MutableStateFlow<UiState<MovieDetailModel>>(UiState.Loading)
    val uiState: StateFlow<UiState<MovieDetailModel>> = _uiState

    private val _showSnackBar = MutableSharedFlow<SnackBarType>()
    val showSnackBar : SharedFlow<SnackBarType> = _showSnackBar

    fun getMovieDetail(movieId: Int) {
        getMovieDetailUseCase.getMovieDetail(movieId)
            .doOnSuccess { model ->
                _movieDetailModel.value = model
            }
            .onEach { state -> _uiState.value = state }
            .launchIn(viewModelScope)
    }

    fun handleUiAction(action: DetailUiAction) {
        when (action) {
            DetailUiAction.ErrorRetryAction -> TODO()
            is DetailUiAction.RecommendedMovieClickAction -> TODO()
            DetailUiAction.OnBackPressClickAction -> sendEvent(MyEvent.OnBackPressed)
            is DetailUiAction.SeeAllClickAction -> TODO()
            is DetailUiAction.DetailButtonClickAction -> {
                when (action.data.type) {
                    MovieDetailActionButtonType.PLAY -> TODO()
                    MovieDetailActionButtonType.SHARE -> TODO()
                    MovieDetailActionButtonType.ADD_FAVORITE -> {
                        movieDetailModel.value?.let { model ->
                            addMyListMovieUseCase.addMyListMovieFromDetail(
                                movieDetailModel = model.copy(
                                    movieDetailInfoModel = model.movieDetailInfoModel.copy(
                                        isFavorite = !model.movieDetailInfoModel.isFavorite
                                    )
                                )
                            ).doOnSuccess {
                                _movieDetailModel.update {
                                    movieDetailModel.value?.copy(
                                        movieDetailInfoModel = model.movieDetailInfoModel.copy(
                                            isFavorite = !model.movieDetailInfoModel.isFavorite
                                        )
                                    )
                                }
                                _showSnackBar.emit(SnackBarType.SUCCESS)
                            }.launchIn(viewModelScope)
                            if (model.movieDetailInfoModel.isAddedToWatchList) {
                                deleteMyListMovieUseCase.deleteMyListMovieFromDetail(
                                    movieDetailModel = model
                                ).launchIn(viewModelScope)
                            }
                        }
                    }

                    MovieDetailActionButtonType.ADD_WATCH_LIST -> {
                        movieDetailModel.value?.let { model ->
                            addMyListMovieUseCase.addMyListMovieFromDetail(
                                movieDetailModel = model.copy(
                                    movieDetailInfoModel = model.movieDetailInfoModel.copy(
                                        isAddedToWatchList = !model.movieDetailInfoModel.isAddedToWatchList
                                    )
                                )
                            ).doOnSuccess {
                                _movieDetailModel.update {
                                    movieDetailModel.value?.copy(
                                        movieDetailInfoModel = model.movieDetailInfoModel.copy(
                                            isAddedToWatchList = !model.movieDetailInfoModel.isAddedToWatchList
                                        )
                                    )
                                }
                            }.launchIn(viewModelScope)
                            if (model.movieDetailInfoModel.isFavorite) {
                                deleteMyListMovieUseCase.deleteMyListMovieFromDetail(
                                    movieDetailModel = model
                                ).launchIn(viewModelScope)
                            }

                        }
                    }
                }
            }
        }
    }
}