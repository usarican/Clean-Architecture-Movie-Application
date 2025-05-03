package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.domain.mapper

import android.os.Build
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.genre.domain.mapper.GenreIdsToGenreNameListMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.genre.domain.model.GenreModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.model.remote.MovieDetailAuthorResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.model.remote.MovieDetailCastResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.model.remote.MovieDetailCreditResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.model.remote.MovieDetailResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.model.remote.MovieDetailReviewResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.model.remote.MovieDetailVideoResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.model.remote.VideoResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.model.remote.VideoSite
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.model.remote.VideoType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.domain.model.MovieDetailReviewModelItem
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.domain.model.MovieDetailCastModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.domain.model.MovieDetailAboutModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.domain.model.MovieDetailInfoModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.domain.model.MovieDetailReviewModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.domain.model.MovieDetailTrailerModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.domain.model.MovieDetailRecommendedModelItem
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.domain.model.MovieDetailTrailerModelItem
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.remote.response.MovieResultResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.domain.model.Language
import com.iusarican.common.utils.helper.FormatHelper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.helper.ImageUrlHelper
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
    fun movieDetailResponseToMovieDetailInfoModel(movieDetailResponse: MovieDetailResponse, language : Language): MovieDetailInfoModel =
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

    fun movieReviewResponseToMovieDetailReviewModel(movieReviewResponse: MovieDetailReviewResponse): MovieDetailReviewModel =
        with(movieReviewResponse) {
            MovieDetailReviewModel(reviews = authorResponses.map { authorResponse ->
                authorResponseToAuthorModel(authorResponse)
            })
        }

    fun movieResponseToMovieDetailRecommendedMovieModel(
        movieResultResponse: MovieResultResponse, genreList: List<GenreModel>
    ): MovieDetailRecommendedModelItem = with(movieResultResponse) {
        MovieDetailRecommendedModelItem(
            movieId = id,
            movieTitle = title,
            movieGenres = genreIdsToGenreNameListMapper.getGenreNames(genreIds, genreList),
            moviePosterImageUrl = imageUrlHelper.getPosterUrl(posterPath),
            movieTMDBScore = voteAverage
        )
    }

    fun movieTrailerResponseToMovieDetailTrailerModel(movieVideoResponse: MovieDetailVideoResponse): MovieDetailTrailerModel =
        with(movieVideoResponse) {
            val filteredMovieTrailerResponse = filterMovieTrailerResponse(videoResponses)
            val orderMovieTrailerResponse = orderVideoTrailersByUpdateTimeDescending(filteredMovieTrailerResponse)
            MovieDetailTrailerModel(trailers = orderMovieTrailerResponse
                .map { response ->
                    MovieDetailTrailerModelItem(
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


    fun authorResponseToAuthorModel(authorResponse: MovieDetailAuthorResponse): MovieDetailReviewModelItem =
        with(authorResponse) {
            MovieDetailReviewModelItem(
                authorName = authorDetailResponse.name,
                review = content,
                updateTime = updatedAt,
                rating = authorDetailResponse.rating,
                authorNickName = authorDetailResponse.username,
                authorProfilePhoto = imageUrlHelper.getProfileUrl(authorDetailResponse.avatarPath)
            )
        }

    private fun castResponseToCastModel(castResponse: MovieDetailCastResponse): MovieDetailCastModel =
        with(castResponse) {
            MovieDetailCastModel(
                characterName = character,
                gender = gender,
                order = order,
                originalName = originalName,
                profileImage = imageUrlHelper.getPosterUrl(profilePath)
            )
        }
}