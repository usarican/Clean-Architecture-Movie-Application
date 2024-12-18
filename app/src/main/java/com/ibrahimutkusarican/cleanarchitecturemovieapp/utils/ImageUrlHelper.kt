package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils

import javax.inject.Inject

class ImageUrlHelper @Inject constructor() {

    fun getPosterUrl(posterPath: String, size: MoviePosterSize = MoviePosterSize.W500): String {
        return "${Constants.MOVIE_IMAGE_API_URL}${size.size}$posterPath"
    }

    fun getBackdropUrl(backdropPath: String, size: BackdropSize = BackdropSize.W780): String {
        return "${Constants.MOVIE_IMAGE_API_URL}${size.size}$backdropPath"
    }

}

enum class MoviePosterSize(val size: String) {
    W92("w92"),
    W154("w154"),
    W185("w185"),
    W342("w342"),
    W500("w500"),
    W780("w780"),
    ORIGINAL("original");
}

enum class BackdropSize(val size: String) {
    W300("w300"),
    W780("w780"),
    W1280("w1280"),
    ORIGINAL("original");
}