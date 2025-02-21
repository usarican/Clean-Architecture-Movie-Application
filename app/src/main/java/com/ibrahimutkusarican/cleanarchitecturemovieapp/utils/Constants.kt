package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils

import com.ibrahimutkusarican.cleanarchitecturemovieapp.BuildConfig

internal object Constants {
    const val MOVIE_API_URL = "https://api.themoviedb.org/3/"
    const val TMDB_AUTH_TOKEN = BuildConfig.TMDB_AUTH_TOKEN
    const val MOVIE_IMAGE_API_URL = "https://image.tmdb.org/t/p/"
    const val EMPTY_STRING = ""
    const val MOVIE_PAGE_SIZE = 20
    const val MY_LIST_PAGE_SIZE = 10
    const val STARTING_PAGE_INDEX = 1
    const val SEARCH_DEBOUNCE_TIME = 1000L
    const val ALL_GENRE_ID = -1
    const val BANNER_EXPLORE_PAGER_DOT_INDICATOR_COUNT = 3
    const val TIME_OUT_VALUE = 15L
}