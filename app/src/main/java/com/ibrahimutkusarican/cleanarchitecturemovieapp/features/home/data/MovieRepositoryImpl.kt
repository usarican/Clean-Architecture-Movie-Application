package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data

import android.util.Log
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ApiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.MovieLocalDataSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.MovieRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import kotlin.time.Duration.Companion.days

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieResultResponseMapper: MovieResultResponseMapper
) : BaseRepository(), MovieRepository {

    private val lock = Mutex()
    private val refreshLock = Mutex()

    override fun getMoviesByType(movieType: MovieType, limit: Int): Flow<ApiState<List<MovieEntity>>> {
        return apiCall {
            val movieEntities = movieLocalDataSource.getMoviesByType(movieType)
            Log.d("Repository","Movie Entites Ids ${movieEntities.map { it.id }} and Type $movieType")
            return@apiCall if (movieEntities.isEmpty()) {
                lock.withLock {
                    fetchAndSaveMoviesFromRemote(
                        movieType = movieType
                    )
                }
            } else {
                if (isDataStale(movieEntities.first())) {
                   lock.withLock {
                       movieLocalDataSource.deleteMoviesByType(movieType)
                       fetchAndSaveMoviesFromRemote(movieType)
                   }
                } else {
                    movieEntities
                }
            }
        }
    }

    override fun refreshMoviesByType(
        movieType: MovieType,
        limit: Int
    ): Flow<ApiState<List<MovieEntity>>> {
        return apiCall {
            refreshLock.withLock {
                fetchAndSaveMoviesFromRemote(movieType)
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
        Log.d("Repository Fetch And Save","Movie Response Ids ${movieResponse.movieResultResponse.map { it.id }} and Type $movieType")
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