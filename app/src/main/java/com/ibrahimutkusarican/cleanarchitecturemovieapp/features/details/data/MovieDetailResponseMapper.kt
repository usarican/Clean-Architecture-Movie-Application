package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.local.VisitedMovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote.MovieDetailResponse
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
                backdropPath = backdropPath,
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