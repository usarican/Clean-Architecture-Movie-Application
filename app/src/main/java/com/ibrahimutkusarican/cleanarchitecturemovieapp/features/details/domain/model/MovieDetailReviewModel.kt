package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model

data class MovieDetailReviewModel(
    val reviews : List<AuthorModel>
)

data class AuthorModel(
    val authorName : String?,
    val review : String?,
    val updateTime : String,
    val rating : Double?,
    val authorNickName : String?,
    val authorProfilePhoto : String?
)
