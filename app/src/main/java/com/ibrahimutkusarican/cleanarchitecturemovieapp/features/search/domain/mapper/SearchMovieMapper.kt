package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.mapper

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.mapper.GenreIdsToGenreNameListMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.model.GenreModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response.MovieResultResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.model.SearchMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.FormatHelper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.ImageUrlHelper
import javax.inject.Inject

class SearchMovieMapper @Inject constructor(
    private val imageUrlHelper: ImageUrlHelper,
    private val genreIdsToGenreNameListMapper: GenreIdsToGenreNameListMapper,
    private val formatHelper: FormatHelper
) {
    fun responseToModel(movieResultResponse: MovieResultResponse,genreList: List<GenreModel>) : SearchMovieModel =
        with(movieResultResponse){
            SearchMovieModel(
                movieId = id,
                movieTitle = title,
                movieContent = overview,
                movieGenres = genreIdsToGenreNameListMapper.getGenreNames(genreIds,genreList),
                moviePosterImageUrl = imageUrlHelper.getPosterUrl(posterPath),
                movieTMDBScore = voteAverage,
                movieReleaseTime = formatHelper.formatReleaseDate(releaseDate, language = "en")
            )
        }
}