package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.mapper

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ResponseMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.local.entity.GenreEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.remote.response.Genre
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