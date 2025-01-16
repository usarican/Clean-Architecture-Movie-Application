package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.mapper

import android.annotation.SuppressLint
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.mapper.GenreIdsToGenreNameListMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.model.GenreModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.BasicMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.BackdropSize
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.FormatHelper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.ImageUrlHelper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.MoviePosterSize
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
        posterSize: MoviePosterSize = MoviePosterSize.W500,
        backdropSize: BackdropSize = BackdropSize.W780
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
                releaseDate = formatHelper.formatReleaseDate(releaseDate, language = "en"),
                movieVotePoint = String.format("%.1f", voteAverage)
            )
        }
    }
}