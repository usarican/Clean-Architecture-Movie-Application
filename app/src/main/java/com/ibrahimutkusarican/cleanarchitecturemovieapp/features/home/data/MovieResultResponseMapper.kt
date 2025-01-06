package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data


import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response.MovieResultResponse
import javax.inject.Inject

class MovieResultResponseMapper @Inject constructor() {
    fun mapResponseToEntity(
        response: MovieResultResponse,
        movieType: MovieType,
        existingMovieTypes: List<MovieType> = emptyList()
    ): MovieEntity {
        return with(response) {
            MovieEntity(
                id = id,
                adult = adult,
                backdropPath = backdropPath,
                genreIds = genreIds,
                originalLanguage = originalLanguage,
                originalTitle = originalTitle,
                overview = overview,
                popularity = popularity,
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title,
                video = video,
                voteAverage = voteAverage,
                voteCount = voteCount,
                movieTypes = existingMovieTypes.toMutableList().apply {
                    if (!contains(movieType)) add(movieType)
                },
                lastFetchedTime = System.currentTimeMillis()
            )
        }
    }

}