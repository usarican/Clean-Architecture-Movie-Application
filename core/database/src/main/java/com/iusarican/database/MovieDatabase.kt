package com.iusarican.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        GenreEntity::class,
        MovieEntity::class,
        MyListMovieEntity::class,
        VisitedMovieEntity::class,
        RegionEntity::class,
        LastSearchEntity::class
    ],
    version = DATABASE_VERSION
)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun genreDao(): GenreDao
    abstract fun movieDao(): MovieDao
    abstract fun myListMovieDao(): MyListMovieDao
    abstract fun lastVisitedMovieDao(): VisitedMovieDao
    abstract fun regionDao(): RegionDao
    abstract fun lastSearchDao(): LastSearchDao

    companion object {
        const val DATABASE_VERSION = 13
    }
}