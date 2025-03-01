package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType


sealed class SeeAllType {

    sealed class SeeAllMovieType(val movieType: MovieType) : SeeAllType() {
        data object NowPlaying : SeeAllMovieType(MovieType.NOW_PLAYING)
        data object Popular : SeeAllMovieType(MovieType.POPULAR)
        data object TopRated : SeeAllMovieType(MovieType.TOP_RATED)
        data object UpComing : SeeAllMovieType(MovieType.UPCOMING)
    }

    data class RecommendationMovies(val movieId: Int) : SeeAllType()
    data class MovieReviews(val movieId: Int) : SeeAllType()
}

enum class SeeAllScreenType {
    NOW_PLAYING, POPULAR, TOP_RATED, UPCOMING, RECOMMENDATION, REVIEWS;

    fun seeAllScreenTypeToSeeAllType(movieId : Int) : SeeAllType {
        return when (this) {
            NOW_PLAYING -> SeeAllType.SeeAllMovieType.NowPlaying
            POPULAR -> SeeAllType.SeeAllMovieType.Popular
            TOP_RATED -> SeeAllType.SeeAllMovieType.TopRated
            UPCOMING -> SeeAllType.SeeAllMovieType.UpComing
            RECOMMENDATION -> SeeAllType.RecommendationMovies(movieId)
            REVIEWS -> SeeAllType.MovieReviews(movieId)
        }
    }
}