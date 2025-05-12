package com.iusarican.data.mapper

import com.iusarican.common.base.ResponseMapper
import com.iusarican.data.local.entity.GenreEntity
import com.iusarican.data.remote.response.Genre
import javax.inject.Inject

class GenreResponseMapper @Inject constructor() : ResponseMapper<GenreEntity, Genre> {

    override fun mapResponseToEntity(response: Genre): GenreEntity =
        with(response){
            GenreEntity(
                genreId = genreId,
                genreName = genreName
            )
        }
}