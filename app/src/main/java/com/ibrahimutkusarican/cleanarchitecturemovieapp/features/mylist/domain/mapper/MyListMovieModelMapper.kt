package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.mapper

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.mapper.GenreIdsToGenreNameListMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.model.GenreModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailInfoModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.BasicMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.data.local.MyListMovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model.MyListMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.FormatHelper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.ImageUrlHelper
import javax.inject.Inject

class MyListMovieModelMapper @Inject constructor(
    private val imageUrlHelper: ImageUrlHelper,
    private val genreIdsToGenreNameListMapper: GenreIdsToGenreNameListMapper,
    private val formatHelper: FormatHelper
) {
    fun entityToModel(entity: MyListMovieEntity,genreList: List<GenreModel>) =
        with(entity){
            MyListMovieModel(
                movieId = movieId,
                genres = genreIdsToGenreNameListMapper.getGenreNames(genreIds, genreList),
                overview = overview,
                posterPath = imageUrlHelper.getPosterUrl(posterPath),
                releaseDate = formatHelper.formatReleaseDate(releaseDate, language = "en"),
                title = title,
                movieRating = formatHelper.formatVoteAverage(voteAverage, language = "en"),
                isFavorite = isFavorite,
                isAddedWatchList = addWatchList
            )
        }

    fun movieDetailModelToMyListMovieModel(movieDetailModel: MovieDetailModel) = with(movieDetailModel){
        MyListMovieModel(
            movieId = movieDetailInfoModel.movieId,
            genres = movieDetailAboutModel.genres,
            overview = movieDetailAboutModel.overview,
            posterPath = movieDetailInfoModel.posterImageUrl,
            releaseDate = movieDetailInfoModel.releaseYear,
            title = movieDetailInfoModel.title,
            movieRating = movieDetailAboutModel.rating,
            isFavorite = movieDetailInfoModel.isFavorite,
            isAddedWatchList = movieDetailInfoModel.isAddedToWatchList
        )
    }

    fun basicMovieModelToMyListMovieModel(basicMovieModel: BasicMovieModel) = with(basicMovieModel){

    }

    fun modelToEntity(model: MyListMovieModel,genreList: List<GenreModel>) = with(model){
        MyListMovieEntity(
            movieId = movieId,
            genreIds = genreIdsToGenreNameListMapper.getGenreIds(genreNames = genres, genres = genreList),
            overview = overview,
            posterPath = posterPath,
            releaseDate = releaseDate,
            title = title,
            voteAverage = movieRating.toDouble(),
            isFavorite = isFavorite,
            addWatchList = isAddedWatchList
        )
    }
}
