package com.iusarican.domain.mapper


import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.genre.domain.model.GenreModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.domain.model.ExploreGenreModel
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