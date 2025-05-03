package com.iusarican.seeAllType

import com.iusarican.MovieType

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

