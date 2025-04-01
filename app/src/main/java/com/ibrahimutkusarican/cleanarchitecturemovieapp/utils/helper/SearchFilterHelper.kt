package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.helper

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.model.SearchFilterModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.StringProvider
import javax.inject.Inject

class SearchFilterHelper @Inject constructor(
    private val stringProvider: StringProvider
) {
    fun getFilterItemList(filterModel: SearchFilterModel?): List<String> {
        return listOf(
            filterModel?.genres?.filter { it.isSelected }?.map { it.genreName } ?: emptyList(),
            filterModel?.regions?.filter { it.isSelected }?.map { it.regionName } ?: emptyList(),
            filterModel?.timePeriods?.filter { it.isSelected }
                ?.map { it.name.ifEmpty { it.nameRes?.let { resId -> stringProvider.getStringFromResource(resId) } ?: "" } }
                ?: emptyList(),
            filterModel?.sorts?.filter { it.isSelected }
                ?.map { stringProvider.getStringFromResource(it.sortNameRes) }
                ?: emptyList()
        ).flatten()
    }

    fun updateGenreSelection(index: Int, searchFilterModel: SearchFilterModel): SearchFilterModel {
        return searchFilterModel.copy(
            genres = searchFilterModel.genres.mapIndexed { i, genre ->
                if (i == index) genre.copy(isSelected = !genre.isSelected) else genre
            }
        )
    }

    fun updateRegionSelection(index: Int, searchFilterModel: SearchFilterModel): SearchFilterModel {
        return searchFilterModel.copy(
            regions = searchFilterModel.regions.mapIndexed { i, region ->
                if (i == index) region.copy(isSelected = !region.isSelected) else region
            }
        )
    }

    fun updateTimePeriodSelection(
        index: Int,
        searchFilterModel: SearchFilterModel
    ): SearchFilterModel {
        return searchFilterModel.copy(
            timePeriods = searchFilterModel.timePeriods.mapIndexed { i, period ->
                if (i == index) period.copy(isSelected = !period.isSelected) else period.copy(
                    isSelected = false
                )
            }
        )
    }

    fun updateSortSelection(index: Int, searchFilterModel: SearchFilterModel): SearchFilterModel {
        return searchFilterModel.copy(
            sorts = searchFilterModel.sorts.mapIndexed { i, sort ->
                if (i == index) sort.copy(isSelected = !sort.isSelected) else sort.copy(isSelected = false)
            }
        )
    }


}