package com.ibrahimutkusarican.cleanarchitecturemovieapp.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.MovieDatabase.Companion.DATABASE_VERSION
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.local.GenreDao
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.local.entity.GenreEntity

@Database(
    entities = [
        GenreEntity::class
    ],
    version = DATABASE_VERSION
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun genreDao(): GenreDao

    companion object {
        const val DATABASE_VERSION = 1
    }
}