package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase

import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.usecase.GetMovieGenresUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.SearchRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.model.SearchFilterModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.mapper.SeeAllMovieModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.model.SeeAllMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.getSuccessOrThrow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FilterMoviesUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository,
    private val seeAllMovieMapper: SeeAllMovieModelMapper,
    private val getMovieGenreUseCase: GetMovieGenresUseCase
) : FilterMoviesUseCase {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun filterMovies(
        searchText: String?,
        searchFilterModel: SearchFilterModel
    ): Flow<PagingData<SeeAllMovieModel>> {
        return getMovieGenreUseCase.getMovieGenresUseCase().flatMapLatest { genreState ->
            val genreList = genreState.getSuccessOrThrow()
            searchRepository.filterMovies(
                releaseYear = searchFilterModel.timePeriods.first { it.isSelected }.time,
                region = searchFilterModel.regions.first { it.isSelected }.regionCode,
                genre = searchFilterModel.genres.filter { it.isSelected }
                    .joinToString(separator = ","),
                sortBy = searchFilterModel.sorts.first { it.isSelected }.sortCode
            ).flatMapLatest { pagingData ->
                if (searchText.isNullOrEmpty()) {
                    flowOf(pagingData.map { movie ->
                        seeAllMovieMapper.responseToModel(movie, genreList)
                    })
                } else {
                    flowOf(
                        pagingData.map { movie ->
                            seeAllMovieMapper.responseToModel(movie, genreList)
                        }.filter { movie ->
                            movie.movieTitle.contains(searchText, ignoreCase = true)
                        }
                    )
                }
            }
        }
    }
}