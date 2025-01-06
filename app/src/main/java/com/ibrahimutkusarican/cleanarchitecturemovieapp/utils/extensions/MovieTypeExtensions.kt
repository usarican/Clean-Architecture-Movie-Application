package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions

import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType

fun MovieType.getStringRes(): Int {
    return when (this) {
        MovieType.POPULAR -> R.string.popular_movies
        MovieType.UPCOMING -> R.string.up_coming_movies
        MovieType.TOP_RATED -> R.string.top_rated_movies
        else -> R.string.popular_movies
    }
}

fun MovieType.paddingWithMovieItems() : Int {
    return when(this){
        MovieType.POPULAR -> R.dimen.medium_padding
        else -> R.dimen.small_padding

    }
}