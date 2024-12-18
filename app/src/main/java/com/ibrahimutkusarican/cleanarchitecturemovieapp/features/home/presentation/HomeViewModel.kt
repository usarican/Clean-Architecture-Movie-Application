package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.usecase.GetMovieGenresUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.usecase.GetHomeMoviesUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.doOnSuccess
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.getSuccessOrThrow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeMoviesUseCase: GetHomeMoviesUseCase
): BaseViewModel() {

    init {
        val movies = getHomeMoviesUseCase.getHomeMoviesUseCase()
        movies.doOnSuccess {
            Log.d(TAG, "Movies: $it")
        }.launchIn(viewModelScope)
    }

    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }

}