package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.main.presentation

import androidx.lifecycle.viewModelScope
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event.MyEvent
import com.iusarican.common.base.BaseViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.widget.MySnackBarModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.main.domain.usecase.LanguageChangeUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.data.SeeAllScreenType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.data.SeeAllType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.domain.model.SettingsModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.domain.usecase.GetSettingsModelUseCase
import com.iusarican.datastore.UserSettingsDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getSettingsModelUseCase: GetSettingsModelUseCase,
    private val languageChangeUseCase: LanguageChangeUseCase,
    private val userSettingsDataStore: UserSettingsDataStore
) : BaseViewModel() {

    private val _navigationFlow = MutableSharedFlow<NavigationRoutes?>()
    val navigationFlow: SharedFlow<NavigationRoutes?> = _navigationFlow.asSharedFlow()

    val userSetting = getSettingsModelUseCase.getSettingsModel()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), SettingsModel())

    private val _bottomNavigationVisibility = MutableStateFlow(true)
    val bottomNavigationVisibility: StateFlow<Boolean> = _bottomNavigationVisibility

    private val _showSnackBar = MutableSharedFlow<MySnackBarModel>()
    val showSnackBar: SharedFlow<MySnackBarModel> = _showSnackBar

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

            is MyEvent.MovieClickEvent -> {
                navigationRouteAction(
                    NavigationRoutes.ClickActionRoutes.MovieDetail(
                        event.movieId,
                        sharedAnimationKey = event.sharedAnimationKey
                    )
                )
                _bottomNavigationVisibility.value = false
            }

            is MyEvent.BannerMovieClickEvent ->  {
                navigationRouteAction(
                    NavigationRoutes.ClickActionRoutes.BannerMovies(
                        event.clickedMovieIndex
                    )
                )
                _bottomNavigationVisibility.value = false
            }

            is MyEvent.GoToExploreEvent -> navigationRouteAction(NavigationRoutes.BottomNavRoutes.Explore)

            is MyEvent.GoToMyListEvent -> {
                navigationRouteAction(
                    NavigationRoutes.BottomNavRoutes.MyList(
                        event.page
                    )
                )
                if (bottomNavigationVisibility.value.not()){
                    _bottomNavigationVisibility.value = !bottomNavigationVisibility.value
                }
            }

            is MyEvent.ChangeBottomNavigationVisibility -> {
                _bottomNavigationVisibility.value = event.isVisible
            }

            is MyEvent.ShowSnackBar -> {
                viewModelScope.launch { _showSnackBar.emit(event.mySnackBarModel) }
            }

            else -> {
            }
        }
    }

    private fun navigationRouteAction(navigationRoutes: NavigationRoutes?) {
        viewModelScope.launch {
            _navigationFlow.emit(navigationRoutes)
        }
    }

    fun languageChanged() {
        viewModelScope.launch {
            if (userSettingsDataStore.getLanguageChangeFlag().first()) {
                userSettingsDataStore.setLanguageChangeFlag(false)
                languageChangeUseCase.languageChangeForMyListMovies()
                    .launchIn(viewModelScope)
            }
        }
    }
}