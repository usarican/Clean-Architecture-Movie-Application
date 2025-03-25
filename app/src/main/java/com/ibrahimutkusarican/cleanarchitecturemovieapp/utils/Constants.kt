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
    const val BANNER_EXPLORE_PAGER_DOT_INDICATOR_COUNT = 3
    const val TIME_OUT_VALUE = 15L
    const val SNACK_BAR_WITH_ACTION_DELAY = 3000L
    const val HOME_SCREEN_BANNER_MOVIES_HEIGHT_RATIO = 0.5F
    const val USER_SETTINGS_DATA_STORE = "USER_SETTINGS_DATASTORE_NAME"
}