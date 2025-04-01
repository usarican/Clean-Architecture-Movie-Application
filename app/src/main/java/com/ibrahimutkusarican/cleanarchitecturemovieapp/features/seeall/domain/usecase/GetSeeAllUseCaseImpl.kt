package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.feature.genre.domain.usecase.GetMovieGenresUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.mapper.MovieDetailModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model.MovieDetailReviewModelItem
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data.SeeAllRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.mapper.SeeAllMovieModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.model.SeeAllMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extension.getSuccessOrThrow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSeeAllUseCaseImpl @Inject constructor(
    private val seeAllRepository: SeeAllRepository,
    private val seeAllMovieModelMapper: SeeAllMovieModelMapper,
    private val getMovieGenreUseCase: GetMovieGenresUseCase,
    private val detailModelMapper: MovieDetailModelMapper
) : GetSeeAllUseCase {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getSeeAllMoviesByType(movieType: MovieType): Flow<PagingData<SeeAllMovieModel>> {
        return getMovieGenreUseCase.getMovieGenresUseCase().flatMapLatest { genreState ->
            val genreList = genreState.getSuccessOrThrow()
            seeAllRepository.getSeeAllMoviesByType(movieType).map { pagingData ->
                pagingData.map { response ->
                    seeAllMovieModelMapper.responseToModel(
                        movieResultResponse = response, genreList = genreList
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getSeeAllRecommendedMovies(movieId: Int): Flow<PagingData<SeeAllMovieModel>> {
        return getMovieGenreUseCase.getMovieGenresUseCase().flatMapLatest { genreState ->
            val genreList = genreState.getSuccessOrThrow()
            seeAllRepository.getRecommendedSeeAllMovies(movieId).map { pagingData ->
                pagingData.map { response ->
                    seeAllMovieModelMapper.responseToModel(
                        movieResultResponse = response, genreList = genreList
                    )
                }
            }
        }
    }

    override fun getMovieReviewsSeeAll(movieId: Int): Flow<PagingData<MovieDetailReviewModelItem>> {
        return seeAllRepository.getMovieReviewsSeeAll(movieId).map { pagingData ->
            pagingData.map { response ->
                detailModelMapper.authorResponseToAuthorModel(response)
            }
        }
    }
}