package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class GenreEntity(
    @PrimaryKey @ColumnInfo(name = "genreId") val genreId : Int,
    @ColumnInfo(name = "genreName") val genreName : String,
)
