package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.explore.domain.mapper


import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.model.GenreModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.explore.domain.model.ExploreGenreModel
import javax.inject.Inject

class ExploreGenreModelMapper @Inject constructor() {
    fun genreModelToExploreGenreModel(genreModel: GenreModel) : ExploreGenreModel {
        return with(genreModel){
            ExploreGenreModel(
                genreId = movieGenreId,
                genreName = movieGenreName
            )
        }
    }
}