package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseViewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.usecase.GetMovieGenresUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMovieGenresUseCase: GetMovieGenresUseCase
): BaseViewModel() {

    init {
        getMovieGenresUseCase.getMovieGenresUseCase()
            .doOnSuccess { genreModels ->
                Log.d(TAG,genreModels.toString())
            }
            .launchIn(viewModelScope)
    }

    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }

}