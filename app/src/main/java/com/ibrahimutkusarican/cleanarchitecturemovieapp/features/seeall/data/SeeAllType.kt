package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data

sealed class SeeAllType  {
    sealed class SeeAllMovieType : SeeAllType() {
        data object NowPlaying : SeeAllMovieType()
        data object Popular : SeeAllMovieType()
        data object TopRated : SeeAllMovieType()
        data object UpComing : SeeAllMovieType()
    }
    data class RecommendationMovies(val movieId : Int) : SeeAllType()
    data class MovieReviews(val movieId: Int) : SeeAllType()
}