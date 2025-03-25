package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.explore.domain.model

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.BasicMovieModel

data class ExploreInitialDataModel(
    val bannerMovies : List<BasicMovieModel> = emptyList(),
    val popularMovies : List<BasicMovieModel> = emptyList(),
    val forYouMovie : BasicMovieModel? = null
)
