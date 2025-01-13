package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response.MovieResultResponse
import javax.inject.Inject

class MovieEntityToResponseMapper @Inject constructor() {
    fun entityToResponse(movieEntity: MovieEntity): MovieResultResponse =
        with(movieEntity) {
            MovieResultResponse(
                adult = adult,
                backdropPath = backdropPath,
                genreIds = genreIds,
                id = id,
                originalTitle = originalTitle,
                originalLanguage = originalLanguage,
                overview = overview,
                voteCount = voteCount,
                voteAverage = voteAverage,
                video = video,
                popularity = popularity,
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title
            )
        }
}