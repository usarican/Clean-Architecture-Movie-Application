package com.iusarican.domain.model

import android.net.Uri

data class MovieShareModel(
    val movieImageUri : Uri,
    val movieId: Int,
    val movieTitle : String
)
