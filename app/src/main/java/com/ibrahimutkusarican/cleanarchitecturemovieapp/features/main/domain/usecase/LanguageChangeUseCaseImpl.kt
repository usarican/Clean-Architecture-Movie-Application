package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.domain.usecase

import android.util.Log
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.MovieDetailRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.data.MyListRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.mapper.MyListMovieModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.getSuccessOrThrow
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject


class LanguageChangeUseCaseImpl @Inject constructor(
    private val myListRepository: MyListRepository,
    private val detailRepository: MovieDetailRepository,
    private val myListMovieModelMapper: MyListMovieModelMapper
) : BaseUseCase(), LanguageChangeUseCase {

    override fun languageChangeForMyListMovies(): Flow<UiState<Boolean>> {
        return execute {
            coroutineScope {
                val myListMoviesIdList = myListRepository.getMyListMoviesIdList()
                val updateJobs =  myListMoviesIdList.map { movieId ->
                    async {
                        try {
                            val movieDetailResponse =
                                detailRepository.getMovieDetailResponse(movieId).first()
                                    .getSuccessOrThrow()
                            val (title, overview, releaseDate) = myListMovieModelMapper.detailResponseForEntityUpdate(
                                movieDetailResponse
                            )
                            myListRepository.updateMyListMovie(
                                movieId,
                                title,
                                overview,
                                releaseDate
                            )
                        } catch (e: Exception) {
                            Log.e("MovieUpdate", "Failed to update movie $movieId", e)
                        }
                    }
                }
                updateJobs.awaitAll()
            }
            true
        }
    }

}