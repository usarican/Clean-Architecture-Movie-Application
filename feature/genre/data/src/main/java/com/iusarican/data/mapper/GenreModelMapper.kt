package com.iusarican.data.mapper

import com.iusarican.common.base.EntityMapper
import com.iusarican.data.local.entity.GenreEntity
import com.iusarican.domain.model.GenreModel
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