package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.mapper

import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.model.local.VisitedMovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.model.remote.MovieDetailResponse
import com.iusarican.common.utils.Constants
import javax.inject.Inject

class MovieDetailResponseMapper @Inject constructor() {
    fun mapResponseToEntity(response: MovieDetailResponse): VisitedMovieEntity =
        with(response) {
            VisitedMovieEntity(
                movieId = movieId,
                title = title,
                posterPath = posterPath,
                overview = overview,
                voteAverage = voteAverage,
                voteCount = voteCount,
                adult = adult,
                backdropPath = backdropPath ?: Constants.EMPTY_STRING,
                budget = budget,
                releaseDate = releaseDate,
                revenue = revenue,
                runtime = runtime,
                status = status,
                tagline = tagline,
                genres = genres
            )
        }
}