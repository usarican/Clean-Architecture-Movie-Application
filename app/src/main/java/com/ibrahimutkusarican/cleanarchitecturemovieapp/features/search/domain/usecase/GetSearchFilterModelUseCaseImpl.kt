package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.repository.GenreRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.SearchRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.mapper.SearchFilterModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.model.SearchFilterModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.model.SortModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.model.TimePeriodModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extension.getSuccessOrThrow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetSearchFilterModelUseCaseImpl @Inject constructor(
    private val genreRepository: GenreRepository,
    private val searchRepository: SearchRepository,
    private val searchFilterModelMapper: SearchFilterModelMapper
) : BaseUseCase(), GetSearchFilterModelUseCase {
    override fun getSearchFilterModel(): Flow<UiState<SearchFilterModel>> {
        return execute {
            combine(
                genreRepository.getMovieGenreList(),
                searchRepository.getRegions()
            ) { genreResult, regionResult ->
                val genreEntities = genreResult.getSuccessOrThrow()
                val regionEntities = regionResult.getSuccessOrThrow()

                val sorts = listOf(
                    SortModel(
                        sortNameRes = R.string.sort_popularity,
                        sortCode = "popularity.desc",
                        isSelected = true
                    ),
                    SortModel(
                        sortNameRes = R.string.sort_release_date,
                        sortCode = "primary_release_date.desc",
                        isSelected = false
                    ),
                    SortModel(
                        sortNameRes = R.string.top_rated,
                        sortCode = "vote_average.desc",
                        isSelected = false
                    )
                )

                val timePeriods = mutableListOf<TimePeriodModel>().apply {
                    add(
                        TimePeriodModel(
                            time = null,
                            nameRes = R.string.all_periods,
                            isSelected = true
                        )
                    )

                    for (year in 2025 downTo 1900) {
                        add(
                            TimePeriodModel(
                                time = year,
                                name = year.toString(),
                                isSelected = false
                            )
                        )
                    }
                }

                SearchFilterModel(
                    genresTitleRes = R.string.genres_title,
                    genres = genreEntities.map { genreEntity ->
                        searchFilterModelMapper.genreEntityToFilterGenreModel(genreEntity)
                    },
                    regionsTitleRes = R.string.regions_title,
                    regions = regionEntities.map { regionEntity ->
                        searchFilterModelMapper.regionEntityToRegionModel(regionEntity)
                    },

                    sortsTitleRes = R.string.sorts_title,
                    sorts = sorts,

                    timePeriodsTitleRes = R.string.time_periods_title,
                    timePeriods = timePeriods
                )
            }.first()
        }
    }
}