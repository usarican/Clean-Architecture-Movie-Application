package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.domain

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.MovieException
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.repository.GenreRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.MovieRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.usecase.RefreshHomeMoviesUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.data.MyListRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.getSuccessOrThrow
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.getSuccessOrThrowOrWait
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject



class LanguageChangeUseCaseImpl @Inject constructor(
    private val genreRepository: GenreRepository,
    private val refreshHomeMoviesUseCase: RefreshHomeMoviesUseCase,
    private val myListRepository: MyListRepository
) : BaseUseCase(), LanguageChangeUseCase {
    override fun languageChangeGenreAndHomeMovies(): Flow<UiState<Boolean>> {
        return combine(
            genreRepository.deleteAllGenreList(), // Flow<ApiState<Unit>>
            refreshHomeMoviesUseCase.refreshHomeMovies() // Flow<UiState<Unit>>
        ) { genreResult, homeResult ->
            genreResult.getSuccessOrThrow() // Throws error if ApiState.Error

            when (homeResult) {
                is UiState.Success -> UiState.Success(true) // Success case
                is UiState.Error -> UiState.Error(homeResult.exception) // Handle error
                is UiState.Loading -> UiState.Loading // Propagate loading state
            }
        }.catch { exp ->
            exp.printStackTrace()
            when (exp) {
                is MovieException -> emit(UiState.Error(exp))
                else -> emit(UiState.Error(MovieException.GeneralException(exp.message)))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun languageChangeForMyListMovies(): Flow<UiState<Boolean>> {
        return execute {
            /*val myListMoviesIdList = myListRepository.getMyListMoviesIdList().first()
            myListMoviesIdList.getSuccessOrThrow()*/
            true
        }
    }
}