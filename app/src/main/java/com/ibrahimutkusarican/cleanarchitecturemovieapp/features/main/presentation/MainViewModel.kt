package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event.MyEvent
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.BaseViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.domain.usecase.LanguageChangeUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.model.SettingsModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.usecase.GetSettingsModelUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.LocaleManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSettingsModelUseCase: GetSettingsModelUseCase,
    private val languageChangeUseCase: LanguageChangeUseCase,
    private val localeManager: LocaleManager
) : BaseViewModel() {

    val navigationChannel = Channel<NavigationRoutes?>(Channel.UNLIMITED)
    val userSetting = getSettingsModelUseCase.getSettingsModel()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), SettingsModel())

    override fun observeMyEvents(event: MyEvent) {
        when (event) {
            is MyEvent.SeeAllClickEvent -> navigationRouteAction(NavigationRoutes.SeeAll(event.movieType))
            is MyEvent.OnBackPressed -> navigationRouteAction(null)
            is MyEvent.SearchBarClickEvent -> navigationRouteAction(NavigationRoutes.Search(event.recommendedMovieId))
            is MyEvent.MovieClickEvent -> navigationRouteAction(NavigationRoutes.MovieDetail(event.movieId))
            is MyEvent.BannerMovieClickEvent -> navigationRouteAction(
                NavigationRoutes.BannerMovies(
                    event.clickedMovieIndex
                )
            )

            else -> {
                Log.d("MainViewModel", "observeMyEvents: $event")
            }
        }
    }

    private fun navigationRouteAction(navigationRoutes: NavigationRoutes?) {
        viewModelScope.launch {
            navigationChannel.send(navigationRoutes)
        }
    }

    fun languageChanged() {
        viewModelScope.launch {
            if (localeManager.getLanguageChangeFlag().first()){
                localeManager.setLanguageChangeFlag(false)
                languageChangeUseCase.languageChangeForMyListMovies()
                    .launchIn(viewModelScope)
            }
        }
    }
}