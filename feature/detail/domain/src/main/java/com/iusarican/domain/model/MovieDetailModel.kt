package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.domain.model

import com.iusarican.domain.model.MovieDetailAboutModel
import com.iusarican.domain.model.MovieDetailInfoModel
import com.iusarican.domain.model.MovieDetailRecommendedModel
import com.iusarican.domain.model.MovieDetailReviewModel
import com.iusarican.domain.model.MovieDetailTrailerModel

data class MovieDetailModel(
    val movieDetailInfoModel: MovieDetailInfoModel,
    val movieDetailAboutModel: MovieDetailAboutModel,
    val movieDetailRecommendedMovies: MovieDetailRecommendedModel,
    val movieDetailReviewModel: MovieDetailReviewModel,
    val movieDetailTrailerModel: MovieDetailTrailerModel
)
