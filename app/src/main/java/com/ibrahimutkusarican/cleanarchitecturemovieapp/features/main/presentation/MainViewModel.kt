package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event.MyEvent
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.BaseViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.domain.usecase.LanguageChangeUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data.SeeAllScreenType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data.SeeAllType
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
            is MyEvent.SeeAllClickEvent -> {
                when (event.seeAllType) {
                    is SeeAllType.MovieReviews -> navigationRouteAction(
                        NavigationRoutes.ClickActionRoutes.SeeAll(
                            screenType = SeeAllScreenType.REVIEWS,
                            movieId = event.seeAllType.movieId
                        )
                    )

                    is SeeAllType.RecommendationMovies -> navigationRouteAction(
                        NavigationRoutes.ClickActionRoutes.SeeAll(
                            screenType = SeeAllScreenType.RECOMMENDATION,
                            movieId = event.seeAllType.movieId
                        )
                    )

                    is SeeAllType.SeeAllMovieType.NowPlaying -> navigationRouteAction(
                        NavigationRoutes.ClickActionRoutes.SeeAll(
                            screenType = SeeAllScreenType.NOW_PLAYING
                        )
                    )

                    is SeeAllType.SeeAllMovieType.Popular -> navigationRouteAction(
                        NavigationRoutes.ClickActionRoutes.SeeAll(
                            screenType = SeeAllScreenType.POPULAR
                        )
                    )

                    is SeeAllType.SeeAllMovieType.TopRated -> navigationRouteAction(
                        NavigationRoutes.ClickActionRoutes.SeeAll(
                            screenType = SeeAllScreenType.TOP_RATED
                        )
                    )

                    is SeeAllType.SeeAllMovieType.UpComing -> navigationRouteAction(
                        NavigationRoutes.ClickActionRoutes.SeeAll(
                            screenType = SeeAllScreenType.UPCOMING
                        )
                    )
                }
            }

            is MyEvent.OnBackPressed -> navigationRouteAction(null)
            is MyEvent.SearchBarClickEvent -> navigationRouteAction(
                NavigationRoutes.ClickActionRoutes.Search(
                    event.recommendedMovieId
                )
            )

            is MyEvent.MovieClickEvent -> navigationRouteAction(
                NavigationRoutes.ClickActionRoutes.MovieDetail(
                    event.movieId
                )
            )

            is MyEvent.BannerMovieClickEvent -> navigationRouteAction(
                NavigationRoutes.ClickActionRoutes.BannerMovies(
                    event.clickedMovieIndex
                )
            )

            is MyEvent.GoToExploreEvent -> navigationRouteAction(NavigationRoutes.BottomNavRoutes.Explore)

            is MyEvent.GoToMyListEvent -> navigationRouteAction(NavigationRoutes.BottomNavRoutes.MyList(event.page))

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
            if (localeManager.getLanguageChangeFlag().first()) {
                localeManager.setLanguageChangeFlag(false)
                languageChangeUseCase.languageChangeForMyListMovies()
                    .launchIn(viewModelScope)
            }
        }
    }
}