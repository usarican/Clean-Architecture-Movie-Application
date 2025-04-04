package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.model

import androidx.annotation.StringRes

data class SearchFilterModel(
    @StringRes val genresTitleRes: Int,
    val genres: List<FilterGenreModel>,

    @StringRes val regionsTitleRes: Int,
    val regions: List<RegionModel>,

    @StringRes val timePeriodsTitleRes: Int,
    val timePeriods: List<TimePeriodModel>,

    @StringRes val sortsTitleRes: Int,
    val sorts: List<SortModel>
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
    val time: Int?,
    @StringRes val nameRes: Int? = null,
    val name : String = "",
    val isSelected: Boolean
)

