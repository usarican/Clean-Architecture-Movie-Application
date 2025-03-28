package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.mapper

import android.os.Build
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.mapper.GenreIdsToGenreNameListMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.model.GenreModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote.AuthorResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote.CastResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote.MovieDetailCreditResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote.MovieDetailResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote.MovieReviewResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote.MovieVideoResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote.VideoResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote.VideoSite
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote.VideoType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.AuthorModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.CastModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailAboutModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailInfoModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailReviewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailTrailerModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.RecommendedMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.TrailerModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response.MovieResultResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.model.Language
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.FormatHelper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.ImageUrlHelper
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

class MovieDetailModelMapper @Inject constructor(
    private val imageUrlHelper: ImageUrlHelper,
    private val formatHelper: FormatHelper,
    private val genreIdsToGenreNameListMapper: GenreIdsToGenreNameListMapper
) {
    fun movieDetailResponseToMovieDetailInfoModel(movieDetailResponse: MovieDetailResponse,language : Language): MovieDetailInfoModel =
        with(movieDetailResponse) {
            MovieDetailInfoModel(
                movieId = movieId,
                title = title,
                tagline = tagline,
                posterImageUrl = imageUrlHelper.getPosterUrl(posterPath),
                backgroundImageUrl = imageUrlHelper.getBackdropUrl(backdropPath),
                releaseYear = formatHelper.formatMovieReleaseDateToYear(
                    releaseDate, language = language
                ),
                isAddedToWatchList = false,
                runtime = formatHelper.formatRuntime(runtime),
                genre = genres.firstOrNull()?.genreName.orEmpty(),
                isFavorite = false,
            )
        }

    fun movieDetailResponseToMovieDetailAboutModel(
        movieDetailResponse: MovieDetailResponse,
        movieDetailCreditResponse: MovieDetailCreditResponse,
        language : Language
    ): MovieDetailAboutModel = with(movieDetailResponse) {
        MovieDetailAboutModel(
            budget = formatHelper.formatMoney(language = language, amount = budget.toLong()),
            revenue = formatHelper.formatMoney(language = language, amount = revenue.toLong()),
            status = status,
            genres = genres.map { it.genreName },
            fullReleaseDate = formatHelper.formatReleaseDate(releaseDate, language = language),
            rating = formatHelper.formatVoteAverageAndVoteCount(
                voteAverage = voteAverage,
                voteCount = voteCount,
                language = language
            ),
            overview = overview,
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
    ): RecommendedMovieModel = with(movieResultResponse) {
        RecommendedMovieModel(
            movieId = id,
            movieTitle = title,
            movieGenres = genreIdsToGenreNameListMapper.getGenreNames(genreIds, genreList),
            moviePosterImageUrl = imageUrlHelper.getPosterUrl(posterPath),
            movieTMDBScore = voteAverage
        )
    }

    fun movieTrailerResponseToMovieDetailTrailerModel(movieVideoResponse: MovieVideoResponse): MovieDetailTrailerModel =
        with(movieVideoResponse) {
            val filteredMovieTrailerResponse = filterMovieTrailerResponse(videoResponses)
            val orderMovieTrailerResponse = orderVideoTrailersByUpdateTimeDescending(filteredMovieTrailerResponse)
            MovieDetailTrailerModel(trailers = orderMovieTrailerResponse
                .map { response ->
                    TrailerModel(
                        name = response.name, key = response.key
                    )
                })
        }

    private fun filterMovieTrailerResponse(list: List<VideoResponse>): List<VideoResponse> {
        return list.filter { response -> response.site == VideoSite.YOUTUBE.value }
            .filter { response -> response.type == VideoType.TRAILER.value }

    }

    private fun orderVideoTrailersByUpdateTimeDescending(list: List<VideoResponse>): List<VideoResponse> {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return list.sortedByDescending { video ->
                ZonedDateTime.parse(video.publishedAt, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                    .toInstant()
            }
        } else {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
            sdf.timeZone = TimeZone.getTimeZone("UTC")

            return list.sortedByDescending { video ->
                val parsedDate: Date? = try {
                    sdf.parse(video.publishedAt)
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
                // Sort by the Date's time in milliseconds; default to 0 if null
                parsedDate?.time ?: 0L
            }
        }
    }


    fun authorResponseToAuthorModel(authorResponse: AuthorResponse): AuthorModel =
        with(authorResponse) {
            AuthorModel(
                authorName = authorDetailResponse.name,
                review = content,
                updateTime = updatedAt,
                rating = authorDetailResponse.rating,
                authorNickName = authorDetailResponse.username,
                authorProfilePhoto = imageUrlHelper.getProfileUrl(authorDetailResponse.avatarPath)
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