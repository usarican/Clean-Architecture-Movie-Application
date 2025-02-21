package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.model

import androidx.annotation.StringRes

data class SearchFilterModel(
    val genres : List<FilterGenreModel>,
    val regions : List<RegionModel>,
    val timePeriods : List<TimePeriodModel>,
    val sorts : List<SortModel>
)

data class FilterGenreModel(
    val genreName : String,
    val genreId : Int,
    val isSelected : Boolean
)

data class RegionModel(
    val regionCode : String,
    val regionName : String,
    val isSelected : Boolean
)

data class SortModel(
    @StringRes val sortNameRes: Int,
    val sortCode: String,
    val isSelected: Boolean
)

data class TimePeriodModel(
    val time: Int,
    @StringRes val nameRes: Int? = null,
    val name : String = "",
    val isSelected: Boolean
)

