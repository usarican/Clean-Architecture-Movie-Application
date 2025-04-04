package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.genre.domain.usecase.GetMovieGenresUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.data.SearchRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.domain.mapper.SeeAllMovieModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.domain.model.SeeAllMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extension.getSuccessOrThrow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchMoviesUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository,
    private val seeAllMovieMapper: SeeAllMovieModelMapper,
    private val getMovieGenreUseCase: GetMovieGenresUseCase
) : SearchMoviesUseCase {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun searchSeeAllMovies(searchText: String): Flow<PagingData<SeeAllMovieModel>> {
        return getMovieGenreUseCase.getMovieGenresUseCase().flatMapLatest { genreState ->
            val genreList = genreState.getSuccessOrThrow()
            searchRepository.searchMovies(searchText).map { pagingData ->
                pagingData.map { response ->
                    seeAllMovieMapper.responseToModel(
                        movieResultResponse = response, genreList = genreList
                    )
                }
            }
        }
    }
}