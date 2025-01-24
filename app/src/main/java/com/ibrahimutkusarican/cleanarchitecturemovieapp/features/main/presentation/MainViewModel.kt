package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.presentation

import androidx.lifecycle.viewModelScope
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.event.MyEvent
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

    val navigationChannel = Channel<NavigationRoutes?>(Channel.UNLIMITED)

    override fun observeMyEvents(event: MyEvent) {
        when (event) {
            is MyEvent.SeeAllClickEvent -> navigationRouteAction(NavigationRoutes.SeeAll(event.movieType))
            is MyEvent.OnBackPressed -> navigationRouteAction(null)
            is MyEvent.SearchBarClickEvent -> navigationRouteAction(NavigationRoutes.Search(event.recommendedMovieId))
            is MyEvent.MovieClickEvent -> navigationRouteAction(NavigationRoutes.MovieDetail(event.movieId))
        }
    }

    private fun navigationRouteAction(navigationRoutes: NavigationRoutes?) {
        viewModelScope.launch {
            navigationChannel.send(navigationRoutes)
        }
    }
}