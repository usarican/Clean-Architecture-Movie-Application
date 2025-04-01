package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extension

import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.local.entity.MovieType

fun MovieType.getStringRes(): Int {
    return when (this) {
        MovieType.POPULAR -> R.string.popular_movies
        MovieType.UPCOMING -> R.string.up_coming_movies
        MovieType.TOP_RATED -> R.string.top_rated_movies
        else -> R.string.popular_movies
    }
}