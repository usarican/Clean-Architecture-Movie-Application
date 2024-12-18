package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.State
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.MovieLocalDataSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.MovieRemoteDataSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response.MovieResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieResultResponseMapper: MovieResultResponseMapper
) : BaseRepository(), MovieRepository {

    override fun getMoviesByType(movieType: MovieType): Flow<State<List<MovieEntity>>> {
        return apiCall {
            val movieEntities = movieLocalDataSource.getMoviesByType(movieType)
            movieEntities.ifEmpty {
                val movieResponse = when (movieType) {
                    MovieType.NOW_PLAYING -> movieRemoteDataSource.getNowPlayingMovies(PAGE_NUMBER)
                    MovieType.POPULAR -> movieRemoteDataSource.getPopularMovies(PAGE_NUMBER)
                    MovieType.TOP_RATED -> movieRemoteDataSource.getTopRatedMovies(PAGE_NUMBER)
                    MovieType.UPCOMING -> movieRemoteDataSource.getUpComingMovies(PAGE_NUMBER)
                }
                fetchAndSaveMovies(
                    movieResponse = movieResponse,
                    movieType = MovieType.UPCOMING
                )
            }
        }
    }

    private suspend fun fetchAndSaveMovies(
        movieType: MovieType,
        movieResponse: MovieResponse
    ): List<MovieEntity> {
        val movieEntities = movieResponse.movieResultResponse.map {
            movieResultResponseMapper.mapResponseToEntity(it, movieType)
        }
        movieLocalDataSource.insertMovies(movieEntities)
        return movieEntities
    }

    companion object {
        private const val PAGE_NUMBER = 1
    }

}