package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils

import androidx.room.TypeConverter
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class Converters {
    private val moshi: Moshi = Moshi.Builder().build()
    private val listType = Types.newParameterizedType(List::class.java, Int::class.javaObjectType)
    private val adapter = moshi.adapter<List<Int>>(listType)

    @TypeConverter
    fun fromGenreIdsList(genreIds: List<Int>): String {
        return adapter.toJson(genreIds) ?: "[]"
    }

    @TypeConverter
    fun toGenreIdsList(genreIdsString: String): List<Int> {
        return adapter.fromJson(genreIdsString) ?: emptyList()
    }

    @TypeConverter
    fun fromMovieType(movieType: MovieType): String {
        return movieType.name // Convert enum to String
    }

    @TypeConverter
    fun toMovieType(movieType: String): MovieType {
        return MovieType.valueOf(movieType) // Convert String back to enum
    }
}