package com.iusarican.domain.model

data class MovieDetailModel(
    val movieDetailInfoModel: MovieDetailInfoModel,
    val movieDetailAboutModel: MovieDetailAboutModel,
    val movieDetailRecommendedMovies: MovieDetailRecommendedModel,
    val movieDetailReviewModel: MovieDetailReviewModel,
    val movieDetailTrailerModel: MovieDetailTrailerModel
)
