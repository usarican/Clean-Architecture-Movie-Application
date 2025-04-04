package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.mapper

import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.genre.data.local.entity.GenreEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.data.local.entities.RegionEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.model.FilterGenreModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.model.RegionModel
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