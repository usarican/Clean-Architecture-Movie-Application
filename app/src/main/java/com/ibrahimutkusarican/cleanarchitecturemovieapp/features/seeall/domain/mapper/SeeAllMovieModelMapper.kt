package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.mapper

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.mapper.GenreIdsToGenreNameListMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.model.GenreModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response.MovieResultResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.local.VisitedMovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.model.SeeAllMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.model.Language
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.FormatHelper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.ImageUrlHelper
import javax.inject.Inject

class SeeAllMovieModelMapper @Inject constructor(
    private val imageUrlHelper: ImageUrlHelper,
    private val genreIdsToGenreNameListMapper: GenreIdsToGenreNameListMapper,
    private val formatHelper: FormatHelper
) {
    fun responseToModel(
        movieResultResponse: MovieResultResponse,
        genreList: List<GenreModel>,
        language: Language = Language.EN
    ): SeeAllMovieModel {
        return with(movieResultResponse) {
            SeeAllMovieModel(
                movieId = id,
                movieTitle = title,
                movieContent = overview,
                movieGenres = genreIdsToGenreNameListMapper.getGenreNames(genreIds, genreList),
                moviePosterImageUrl = imageUrlHelper.getPosterUrl(posterPath),
                movieTMDBScore = voteAverage,
                movieReleaseTime = formatHelper.formatReleaseDate(releaseDate, language = language)
            )
        }
    }

    fun entityToModel(entity: VisitedMovieEntity,language: Language = Language.EN) : SeeAllMovieModel {
        return with(entity) {
            SeeAllMovieModel(
                movieId = movieId,
                movieTitle = title,
                movieContent = overview,
                movieGenres = genres.map { it.genreName },
                moviePosterImageUrl = imageUrlHelper.getPosterUrl(posterPath),
                movieTMDBScore = voteAverage,
                movieReleaseTime = formatHelper.formatReleaseDate(releaseDate, language = language)
            )
        }
    }
}