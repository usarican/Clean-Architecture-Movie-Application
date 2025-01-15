package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.usecase.GetMovieGenresUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.SearchRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.mapper.SeeAllMovieModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.model.SeeAllMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.getSuccessOrThrow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchSeeAllMoviesUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository,
    private val seeAllMovieMapper: SeeAllMovieModelMapper,
    private val getMovieGenreUseCase: GetMovieGenresUseCase
) : SearchSeeAllMoviesUseCase {
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