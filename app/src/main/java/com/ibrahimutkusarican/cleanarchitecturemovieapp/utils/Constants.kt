package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils

import com.ibrahimutkusarican.cleanarchitecturemovieapp.BuildConfig

internal object Constants {
    const val API_KEY = BuildConfig.PLACES_API_KEY
    const val MOVIE_API_URL = "https://api.themoviedb.org/3/"
    const val TMDB_AUTH_TOKEN = BuildConfig.TMDB_AUTH_TOKEN
}