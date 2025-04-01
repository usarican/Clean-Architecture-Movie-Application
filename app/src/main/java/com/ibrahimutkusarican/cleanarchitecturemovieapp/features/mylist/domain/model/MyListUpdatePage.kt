package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model

import androidx.annotation.StringRes
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model.MovieDetailModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.BasicMovieModel

enum class MyListUpdatePage(
    val index: Int,
    @StringRes val title: Int
) {
    FAVORITE(index = 0, R.string.favorite), WATCH_LIST(index = 1, title = R.string.watch_list);

    fun thisMovieModelShouldDeleteFromMyList(basicMovieModel: BasicMovieModel): Boolean {
        return when (this) {
            FAVORITE -> basicMovieModel.isFavorite && basicMovieModel.isAddedToWatchList.not()
            WATCH_LIST -> basicMovieModel.isAddedToWatchList && basicMovieModel.isFavorite.not()
        }
    }

    fun thisMovieModelShouldDeleteFromMyList(movieDetailModel: MovieDetailModel): Boolean {
        return when (this) {
            FAVORITE -> movieDetailModel.movieDetailInfoModel.isFavorite && movieDetailModel.movieDetailInfoModel.isAddedToWatchList.not()
            WATCH_LIST -> movieDetailModel.movieDetailInfoModel.isAddedToWatchList && movieDetailModel.movieDetailInfoModel.isFavorite.not()
        }
    }

    fun thisMovieModelShouldDeleteFromMyList(myListMovieModel: MyListMovieModel): Boolean {
        return when (this) {
            FAVORITE -> myListMovieModel.isFavorite && myListMovieModel.isAddedWatchList.not()
            WATCH_LIST -> myListMovieModel.isAddedWatchList && myListMovieModel.isFavorite.not()
        }
    }

    fun getUpdatedMovieModel(basicMovieModel: BasicMovieModel): BasicMovieModel =
        when (this) {
            FAVORITE -> basicMovieModel.copy(
                isFavorite = basicMovieModel.isFavorite.not()
            )

            WATCH_LIST -> basicMovieModel.copy(
                isAddedToWatchList = basicMovieModel.isAddedToWatchList.not()
            )
        }

    fun getUpdatedMovieModel(movieDetailModel: MovieDetailModel): MovieDetailModel =
        when (this) {
            FAVORITE -> movieDetailModel.copy(
                movieDetailInfoModel = movieDetailModel.movieDetailInfoModel.copy(
                    isFavorite = movieDetailModel.movieDetailInfoModel.isFavorite.not()
                )
            )

            WATCH_LIST -> movieDetailModel.copy(
                movieDetailInfoModel = movieDetailModel.movieDetailInfoModel.copy(
                    isAddedToWatchList = movieDetailModel.movieDetailInfoModel.isAddedToWatchList.not()
                )
            )
        }

    fun getUpdatedMovieModel(myListMovieModel: MyListMovieModel): MyListMovieModel =
        when (this) {
            FAVORITE -> myListMovieModel.copy(
                isFavorite = myListMovieModel.isFavorite.not()
            )

            WATCH_LIST -> myListMovieModel.copy(
                isAddedWatchList = myListMovieModel.isAddedWatchList.not()
            )
        }

    companion object {
        fun findPageByIndex(index : Int) = entries.firstOrNull { it.index == index } ?: FAVORITE
    }
}