package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.mapper

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.mapper.GenreIdsToGenreNameListMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.model.GenreModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.HomeMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.ImageUrlHelper
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

class HomeMovieModelMapper @Inject constructor(
    private val imageUrlHelper: ImageUrlHelper,
    private val genreIdsToGenreNameListMapper: GenreIdsToGenreNameListMapper
) {
    fun mapEntityToModel(entity: MovieEntity, genreList: List<GenreModel>): HomeMovieModel {
        return with(entity) {
            HomeMovieModel(
                movieId = id,
                movieTitle = title,
                movieGenres = genreIdsToGenreNameListMapper.getGenreNames(
                    genreIds = genreIds, genres = genreList
                ),
                moviePosterImageUrl = imageUrlHelper.getPosterUrl(posterPath),
                movieBackdropImageUrl = imageUrlHelper.getBackdropUrl(backdropPath),
                movieTMDBScore = BigDecimal(voteAverage).setScale(1, RoundingMode.HALF_UP)
                    .toDouble()
            )
        }
    }
}