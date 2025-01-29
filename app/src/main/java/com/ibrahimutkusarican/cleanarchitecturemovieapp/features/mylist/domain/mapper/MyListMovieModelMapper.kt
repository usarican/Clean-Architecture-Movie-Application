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
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title,
                movieRating = voteAverage,
                isFavorite = isFavorite,
                isAddedWatchList = addWatchList
            )
        }

    fun movieDetailModelToMyListMovieEntity(movieDetailModel: MovieDetailModel,genreList: List<GenreModel>) = with(movieDetailModel){
        MyListMovieEntity(
            movieId = movieDetailInfoModel.movieId,
            genreIds = genreIdsToGenreNameListMapper.getGenreIds(genreNames = movieDetailAboutModel.genres, genres = genreList),
            overview = movieDetailAboutModel.overview,
            posterPath = movieDetailInfoModel.posterImageUrl,
            releaseDate = movieDetailAboutModel.fullReleaseDate,
            title = movieDetailInfoModel.title,
            voteAverage = movieDetailAboutModel.rating,
            isFavorite = movieDetailInfoModel.isFavorite,
            addWatchList = movieDetailInfoModel.isAddedToWatchList
        )
    }

    fun basicMovieModelToMyListMovieEntity(basicMovieModel: BasicMovieModel,genreList: List<GenreModel>) = with(basicMovieModel){
        MyListMovieEntity(
            movieId = movieId,
            genreIds = genreIdsToGenreNameListMapper.getGenreIds(genreNames = movieGenres, genres = genreList),
            overview = movieOverview,
            posterPath = moviePosterImageUrl,
            releaseDate = releaseDate,
            title = movieTitle,
            voteAverage = movieVotePoint,
            isFavorite = isFavorite,
            addWatchList = isAddedToWatchList
        )
    }

    fun modelToEntity(model: MyListMovieModel,genreList: List<GenreModel>) = with(model){
        MyListMovieEntity(
            movieId = movieId,
            genreIds = genreIdsToGenreNameListMapper.getGenreIds(genreNames = genres, genres = genreList),
            overview = overview,
            posterPath = posterPath,
            releaseDate = releaseDate,
            title = title,
            voteAverage = movieRating,
            isFavorite = isFavorite,
            addWatchList = isAddedWatchList
        )
    }
}
