package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils

import androidx.room.TypeConverter
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.remote.response.Genre
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class Converters {
    private val moshi: Moshi = Moshi.Builder().build()
    private val listType = Types.newParameterizedType(List::class.java, Int::class.javaObjectType)
    private val adapter = moshi.adapter<List<Int>>(listType)
    private val movieTypeListType =
        Types.newParameterizedType(List::class.java, MovieType::class.java)
    private val movieTypeListAdapter = moshi.adapter<List<MovieType>>(movieTypeListType)
    private val genreListType =
        Types.newParameterizedType(List::class.java, Genre::class.java)
    private val genreListAdapter = moshi.adapter<List<Genre>>(genreListType)

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
    fun fromMovieTypeList(movieTypes: List<MovieType>): String {
        return movieTypeListAdapter.toJson(movieTypes) ?: "[]"
    }

    @TypeConverter
    fun toMovieTypeList(movieTypesString: String): List<MovieType> {
        return movieTypeListAdapter.fromJson(movieTypesString) ?: emptyList()
    }

    @TypeConverter
    fun fromGenreList(genres: List<Genre>?): String {
        return genreListAdapter.toJson(genres) ?: "[]"
    }

    @TypeConverter
    fun toGenreList(genresString : String): List<Genre> {
        return genreListAdapter.fromJson(genresString).orEmpty()
    }

}