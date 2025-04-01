package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.genre.domain.usecase.GetMovieGenresUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.data.SearchRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.model.SearchFilterModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.domain.mapper.SeeAllMovieModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.domain.model.SeeAllMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extension.getSuccessOrThrow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FilterMoviesUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository,
    private val seeAllMovieMapper: SeeAllMovieModelMapper,
    private val getMovieGenreUseCase: GetMovieGenresUseCase
) : FilterMoviesUseCase {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun filterMovies(
        searchFilterModel: SearchFilterModel
    ): Flow<PagingData<SeeAllMovieModel>> {
        return getMovieGenreUseCase.getMovieGenresUseCase().flatMapLatest { genreState ->
            val genreList = genreState.getSuccessOrThrow()
            val releaseYear = searchFilterModel.timePeriods.firstOrNull { it.isSelected }?.time
            val region = searchFilterModel.regions.firstOrNull { it.isSelected }?.regionCode
            val genre = searchFilterModel.genres.filter { it.isSelected }.map { it.genreId }
                .joinToString(separator = ",")
                .takeIf { it.isNotEmpty() }
            val sortBy = searchFilterModel.sorts.first { it.isSelected }.sortCode
            searchRepository.filterMovies(
                releaseYear = releaseYear,
                region = region,
                genre = genre,
                sortBy = sortBy
            ).map { pagingData ->
                pagingData.map { movie ->
                    seeAllMovieMapper.responseToModel(movie, genreList)
                }
            }
        }
    }
}