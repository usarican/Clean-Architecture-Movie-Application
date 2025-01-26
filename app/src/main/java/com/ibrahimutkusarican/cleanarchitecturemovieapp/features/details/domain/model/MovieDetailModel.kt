package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model

data class MovieDetailModel(
    val movieDetailInfoModel: MovieDetailInfoModel,
    val movieDetailAboutModel: MovieDetailAboutModel,
    val movieDetailRecommendedMovies: MovieDetailRecommendedMovieModel,
    val movieDetailReviewModel: MovieDetailReviewModel,
    val movieDetailTrailerModel: MovieDetailTrailerModel
)
