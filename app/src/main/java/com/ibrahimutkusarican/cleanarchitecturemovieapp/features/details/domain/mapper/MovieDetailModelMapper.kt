package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.mapper

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.mapper.GenreIdsToGenreNameListMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.model.GenreModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote.AuthorResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote.CastResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote.MovieDetailCreditResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote.MovieDetailResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote.MovieReviewResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote.MovieVideoResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.AuthorModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.CastModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailAboutModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailInfoModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailRecommendedMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailReviewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailTrailerModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.TrailerModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response.MovieResultResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.FormatHelper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.ImageUrlHelper
import javax.inject.Inject

class MovieDetailModelMapper @Inject constructor(
    private val imageUrlHelper: ImageUrlHelper,
    private val formatHelper: FormatHelper,
    private val genreIdsToGenreNameListMapper: GenreIdsToGenreNameListMapper
) {
    fun movieDetailResponseToMovieDetailInfoModel(movieDetailResponse: MovieDetailResponse): MovieDetailInfoModel =
        with(movieDetailResponse) {
            MovieDetailInfoModel(
                movieId = movieId,
                title = title,
                tagline = tagline,
                posterImageUrl = imageUrlHelper.getPosterUrl(posterPath),
                backgroundImageUrl = imageUrlHelper.getBackdropUrl(backdropPath),
                releaseYear = formatHelper.formatMovieReleaseDateToYear(
                    releaseDate, language = "en"
                ),
                isAddedToWatchList = false,
                runtime = formatHelper.formatRuntime(runtime),
                genre = genres.firstOrNull()?.genreName.orEmpty()
            )
        }

    fun movieDetailResponseToMovieDetailAboutModel(
        movieDetailResponse: MovieDetailResponse,
        movieDetailCreditResponse: MovieDetailCreditResponse
    ): MovieDetailAboutModel = with(movieDetailResponse) {
        MovieDetailAboutModel(budget = budget.toString(),
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
            })
    }

    fun movieReviewResponseToMovieDetailReviewModel(movieReviewResponse: MovieReviewResponse): MovieDetailReviewModel =
        with(movieReviewResponse) {
            MovieDetailReviewModel(reviews = authorResponses.map { authorResponse ->
                authorResponseToAuthorModel(authorResponse)
            })
        }

    fun movieResponseToMovieDetailRecommendedMovieModel(
        movieResultResponse: MovieResultResponse, genreList: List<GenreModel>
    ): MovieDetailRecommendedMovieModel = with(movieResultResponse) {
        MovieDetailRecommendedMovieModel(
            movieId = id,
            movieTitle = title,
            movieGenres = genreIdsToGenreNameListMapper.getGenreNames(genreIds, genreList),
            moviePosterImageUrl = imageUrlHelper.getPosterUrl(posterPath),
            movieTMDBScore = voteAverage
        )
    }

    fun movieTrailerResponseToMovieDetailTrailerModel(movieVideoResponse: MovieVideoResponse): MovieDetailTrailerModel =
        with(movieVideoResponse) {
            MovieDetailTrailerModel(trailers = movieVideoResponse.videoResponses.map { response ->
                TrailerModel(
                    name = response.name, key = response.key
                )
            })
        }


    private fun authorResponseToAuthorModel(authorResponse: AuthorResponse): AuthorModel =
        with(authorResponse) {
            AuthorModel(
                authorName = authorDetailResponse.name,
                review = content,
                updateTime = updatedAt,
                rating = authorDetailResponse.rating,
                authorNickName = authorDetailResponse.username,
                authorProfilePhoto = authorDetailResponse.avatarPath
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