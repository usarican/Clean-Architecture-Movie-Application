package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.mapper

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote.CastResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote.MovieDetailCreditResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote.MovieDetailResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.CastModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailAboutModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.FormatHelper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.ImageUrlHelper
import javax.inject.Inject

class MovieDetailModelMapper @Inject constructor(
    private val imageUrlHelper: ImageUrlHelper,
    private val formatHelper: FormatHelper
) {
    fun movieDetailResponseToMovieDetailModel(movieDetailResponse: MovieDetailResponse): MovieDetailModel =
        with(movieDetailResponse) {
            MovieDetailModel(
                movieId = movieId,
                title = title,
                tagline = tagline,
                posterImageUrl = imageUrlHelper.getPosterUrl(posterPath),
                backgroundImageUrl = imageUrlHelper.getBackdropUrl(backdropPath),
                releaseYear = formatHelper.formatMovieReleaseDateToYear(
                    releaseDate,
                    language = "en"
                ),
                isAddedToWatchList = false,
                runtime = formatHelper.formatRuntime(runtime)
            )
        }

    fun movieDetailResponseToMovieDetailAboutModel(
        movieDetailResponse: MovieDetailResponse,
        movieDetailCreditResponse: MovieDetailCreditResponse
    ): MovieDetailAboutModel =
        with(movieDetailResponse) {
            MovieDetailAboutModel(
                budget = budget.toString(),
                revenue = revenue.toString(),
                status = status,
                genres = genres.map { it.genreName },
                fullReleaseDate = formatHelper.formatReleaseDate(releaseDate, language = "en"),
                voteCount = voteCount,
                voteAverage = voteAverage,
                casts = movieDetailCreditResponse.castResponse.map { castResponse ->
                    castResponseToCastModel(
                        castResponse
                    )
                }
            )
        }

    private fun castResponseToCastModel(castResponse: CastResponse): CastModel =
        with(castResponse) {
            CastModel(
                characterName = character,
                gender = gender,
                order = order,
                originalName = originalName,
                profileImage = imageUrlHelper.getPosterUrl(profilePath)
            )
        }
}