package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "genres")
data class GenreEntity(
    @ColumnInfo(name = "genreId") val genreId : Int,
    @ColumnInfo(name = "genreName") val genreName : String,
)
