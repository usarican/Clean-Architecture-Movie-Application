package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ApiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.MovieLocalDataSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.MovieRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.time.Duration.Companion.days

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieResultResponseMapper: MovieResultResponseMapper
) : BaseRepository(), MovieRepository {

    override fun getMoviesByType(movieType: MovieType): Flow<ApiState<List<MovieEntity>>> {
        return apiCall {
            val movieEntities = movieLocalDataSource.getMoviesByType(movieType)
            return@apiCall if (movieEntities.isEmpty()) {
                fetchAndSaveMoviesFromRemote(
                    movieType = movieType
                )
            } else {
                if (isDataStale(movieEntities.first())) {
                    movieLocalDataSource.deleteAllMovies()
                    fetchAndSaveMoviesFromRemote(movieType)
                } else {
                    movieEntities
                }
            }
        }
    }

    private suspend fun fetchAndSaveMoviesFromRemote(
        movieType: MovieType,
    ): List<MovieEntity> {
        val movieResponse = when (movieType) {
            MovieType.NOW_PLAYING -> movieRemoteDataSource.getNowPlayingMovies(PAGE_NUMBER)
            MovieType.POPULAR -> movieRemoteDataSource.getPopularMovies(PAGE_NUMBER)
            MovieType.TOP_RATED -> movieRemoteDataSource.getTopRatedMovies(PAGE_NUMBER)
            MovieType.UPCOMING -> movieRemoteDataSource.getUpComingMovies(PAGE_NUMBER)
        }
        val movieEntities = movieResponse.movieResultResponse.map { response ->
            val existingMovie = movieLocalDataSource.getMovieById(response.id)
            movieResultResponseMapper.mapResponseToEntity(
                response = response,
                movieType = movieType,
                existingMovieTypes = existingMovie?.movieTypes ?: emptyList()
            )
        }
        movieLocalDataSource.insertMovies(movieEntities)
        return movieEntities
    }

    private fun isDataStale(movieEntity: MovieEntity): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastFetchedTime = movieEntity.lastFetchedTime
        return (currentTime - lastFetchedTime) > MOVIE_CACHE_TIME
    }

    companion object {
        private const val PAGE_NUMBER = 1
        private val MOVIE_CACHE_TIME = 1.days.inWholeMilliseconds
    }

}