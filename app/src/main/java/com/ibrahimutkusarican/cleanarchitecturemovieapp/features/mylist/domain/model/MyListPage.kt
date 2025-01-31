package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model

import androidx.annotation.StringRes
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R

enum class MyListPage(
    val index: Int,
    @StringRes val title: Int
) {
    FAVORITE(index = 0, R.string.favorite), WATCH_LIST(index = 1, title = R.string.watch_list)
}