package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.domain.model

data class MovieDetailReviewModelItem(
    val authorName : String?,
    val review : String?,
    val updateTime : String,
    val rating : Double?,
    val authorNickName : String?,
    val authorProfilePhoto : String?
)