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

    // For List<MovieType>
    private val movieTypeListType = Types.newParameterizedType(List::class.java, MovieType::class.java)
    private val movieTypeListAdapter = moshi.adapter<List<MovieType>>(movieTypeListType)

    @TypeConverter
    fun fromMovieTypeList(movieTypes: List<MovieType>): String {
        return movieTypeListAdapter.toJson(movieTypes) ?: "[]"
    }

    @TypeConverter
    fun toMovieTypeList(movieTypesString: String): List<MovieType> {
        return movieTypeListAdapter.fromJson(movieTypesString) ?: emptyList()
    }

}