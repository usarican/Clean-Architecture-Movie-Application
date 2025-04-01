package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.feature.genre.domain.mapper

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.base.EntityMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.feature.genre.data.local.entity.GenreEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.feature.genre.domain.model.GenreModel
import javax.inject.Inject

class GenreModelMapper @Inject constructor() : EntityMapper<GenreEntity, GenreModel> {
    override fun mapEntityToModel(entity: GenreEntity): GenreModel {
        return with(entity){
            GenreModel(
                movieGenreId = genreId,
                movieGenreName = genreName
            )
        }
    }
}