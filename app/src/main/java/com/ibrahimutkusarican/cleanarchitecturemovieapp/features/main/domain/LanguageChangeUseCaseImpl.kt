package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.domain

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.repository.GenreRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.usecase.RefreshHomeMoviesUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.data.MyListRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.getSuccessOrThrow
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class LanguageChangeUseCaseImpl @Inject constructor(
    private val genreRepository: GenreRepository,
    private val refreshHomeMoviesUseCase: RefreshHomeMoviesUseCase,
    private val myListRepository: MyListRepository
) : BaseUseCase(), LanguageChangeUseCase {
    override fun languageChangeForHomeMovies(): Flow<UiState<Boolean>> {
        return refreshHomeMoviesUseCase.refreshHomeMovies().map { state ->
            state.map {
                true
            }
        }
    }

    override fun languageChangeForMyListMovies(): Flow<UiState<Boolean>> {
        return execute {
            /*val myListMoviesIdList = myListRepository.getMyListMoviesIdList().first()
            myListMoviesIdList.getSuccessOrThrow()*/
            true
        }
    }

    override fun languageChangeForGenre(): Flow<UiState<Boolean>> {
        return execute {
            genreRepository.deleteAllGenreList().first().getSuccessOrThrow()
        }
    }
}