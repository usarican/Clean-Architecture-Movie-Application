package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.mapper

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.feature.genre.data.local.entity.GenreEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.local.entities.RegionEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.model.FilterGenreModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.model.RegionModel
import javax.inject.Inject

class SearchFilterModelMapper @Inject constructor() {
    fun regionEntityToRegionModel(regionEntity: RegionEntity) =
        with(regionEntity){
            RegionModel(
                regionCode = regionCode,
                regionName = regionName,
                isSelected = false
            )
        }

    fun genreEntityToFilterGenreModel(genreEntity: GenreEntity) =
        with(genreEntity){
            FilterGenreModel(
                genreName = genreName,
                genreId = genreId,
                isSelected = false
            )
        }
}