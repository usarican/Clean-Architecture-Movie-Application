package com.iusarican.seeAllType

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