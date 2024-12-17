package com.ibrahimutkusarican.cleanarchitecturemovieapp.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.MovieDatabase.Companion.DATABASE_VERSION
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.local.GenreDao
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.local.entity.GenreEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.MovieDao
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Converters

@Database(
    entities = [
        GenreEntity::class,
        MovieEntity::class
    ],
    version = DATABASE_VERSION
)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun genreDao(): GenreDao
    abstract fun movieDao(): MovieDao

    companion object {
        const val DATABASE_VERSION = 1
    }
}