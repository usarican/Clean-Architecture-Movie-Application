package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.presentation

import androidx.lifecycle.viewModelScope
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event.MyEvent
import com.iusarican.common.base.BaseViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.domain.model.ExploreInitialDataModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.domain.usecase.GetExploreInitialDataUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extension.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val getExploreInitialDataUseCase: GetExploreInitialDataUseCase
) : BaseViewModel() {

    private val _exploreInitialData = MutableStateFlow(ExploreInitialDataModel())
    val exploreInitialData: StateFlow<ExploreInitialDataModel> = _exploreInitialData

    private val _exploreUiState =
        MutableStateFlow<UiState<ExploreInitialDataModel>>(UiState.Loading)
    val exploreUiState: StateFlow<UiState<ExploreInitialDataModel>> = _exploreUiState

    init {
        getExploreInitialData()
    }

    fun handleUiAction(action : ExploreUiAction){
        when(action){
            is ExploreUiAction.BannerMovieClickAction -> sendEvent(MyEvent.MovieClickEvent(action.movieId))
            is ExploreUiAction.MovieClickAction -> sendEvent(MyEvent.MovieClickEvent(action.movieId,action.sharedAnimationKey))
            is ExploreUiAction.SeeAllClickAction -> sendEvent(MyEvent.SeeAllClickEvent(action.seeAllType))
            ExploreUiAction.ErrorRetryAction -> getExploreInitialData()
            is ExploreUiAction.SearchBarClickAction -> {
                sendEvent(MyEvent.SearchBarClickEvent(action.recommendedMovieId))
            }

            is ExploreUiAction.ForYouMovieClickAction -> sendEvent(MyEvent.MovieClickEvent(action.movieId))
        }
    }

    private fun getExploreInitialData() {
        getExploreInitialDataUseCase.getExploreInitialData()
            .doOnSuccess { data -> _exploreInitialData.value = data }
            .onEach { state -> _exploreUiState.value = state }
            .launchIn(viewModelScope)
    }
}