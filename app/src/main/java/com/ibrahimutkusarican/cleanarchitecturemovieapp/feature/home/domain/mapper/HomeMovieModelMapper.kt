package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.domain.mapper

import android.annotation.SuppressLint
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.genre.domain.mapper.GenreIdsToGenreNameListMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.genre.domain.model.GenreModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.local.entity.MovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.remote.response.MovieResultResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.domain.model.BasicMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.domain.model.Language
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.helper.BackdropSize
import com.iusarican.common.utils.helper.FormatHelper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.helper.ImageUrlHelper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.helper.MoviePosterSize
import javax.inject.Inject

class HomeMovieModelMapper @Inject constructor(
    private val imageUrlHelper: ImageUrlHelper,
    private val genreIdsToGenreNameListMapper: GenreIdsToGenreNameListMapper,
    private val formatHelper: FormatHelper
) {
    @SuppressLint("DefaultLocale")
    fun mapEntityToModel(
        entity: MovieEntity,
        genreList: List<GenreModel>,
        posterSize: MoviePosterSize = MoviePosterSize.W342,
        backdropSize: BackdropSize = BackdropSize.W780,
        language : Language
    ): BasicMovieModel {
        return with(entity) {
            BasicMovieModel(
                movieId = id,
                movieTitle = title,
                movieGenres = genreIdsToGenreNameListMapper.getGenreNames(
                    genreIds = genreIds, genres = genreList
                ),
                moviePosterImageUrl = imageUrlHelper.getPosterUrl(
                    posterPath = posterPath, size = posterSize
                ),
                movieBackdropImageUrl = imageUrlHelper.getBackdropUrl(
                    backdropPath = backdropPath, size = backdropSize
                ),
                movieOverview = overview,
                releaseDate = formatHelper.formatReleaseDate(releaseDate, language = language),
                movieVotePoint = String.format("%.1f", voteAverage)
            )
        }
    }

    @SuppressLint("DefaultLocale")
    fun mapResponseToModel(
        movieResultResponse: MovieResultResponse, genreList: List<GenreModel>,
        posterSize: MoviePosterSize = MoviePosterSize.W500,
        backdropSize: BackdropSize = BackdropSize.W780,
        language : Language
    ): BasicMovieModel {
        return with(movieResultResponse) {
            BasicMovieModel(
                movieId = id,
                movieTitle = title,
                movieGenres = genreIdsToGenreNameListMapper.getGenreNames(
                    genreIds = genreIds, genres = genreList
                ),
                moviePosterImageUrl = imageUrlHelper.getPosterUrl(
                    posterPath = posterPath, size = posterSize
                ),
                movieBackdropImageUrl = imageUrlHelper.getBackdropUrl(
                    backdropPath = backdropPath, size = backdropSize
                ),
                movieOverview = overview,
                releaseDate = formatHelper.formatReleaseDate(releaseDate, language = language),
                movieVotePoint = String.format("%.1f", voteAverage)
            )
        }
    }
}