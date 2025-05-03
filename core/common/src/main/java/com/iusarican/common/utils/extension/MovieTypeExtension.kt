package com.iusarican.common.utils.extension

import com.iusarican.MovieType
import com.iusarican.common.R

fun MovieType.getStringRes(): Int {
    return when (this) {
        MovieType.POPULAR -> R.string.popular_movies
        MovieType.UPCOMING -> R.string.up_coming_movies
        MovieType.TOP_RATED -> R.string.top_rated_movies
        MovieType.NOW_PLAYING -> R.string.popular_movies
    }
}