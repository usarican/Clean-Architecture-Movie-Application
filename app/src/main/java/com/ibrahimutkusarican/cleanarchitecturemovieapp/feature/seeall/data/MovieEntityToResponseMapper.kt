package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.data

import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.local.entity.MovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.remote.response.MovieResultResponse
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