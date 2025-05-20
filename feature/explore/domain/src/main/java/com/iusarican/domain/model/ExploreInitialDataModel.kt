package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.domain.model

import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.domain.model.BasicMovieModel

data class ExploreInitialDataModel(
    val bannerMovies : List<BasicMovieModel> = emptyList(),
    val popularMovies : List<BasicMovieModel> = emptyList(),
    val forYouMovie : BasicMovieModel? = null
)
