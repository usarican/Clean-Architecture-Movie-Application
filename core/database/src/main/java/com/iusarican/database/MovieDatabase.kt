package com.iusarican.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.iusarican.data.local.GenreDao
import com.iusarican.data.local.entity.GenreEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.local.MovieDao
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.local.entity.MovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.mylist.data.local.MyListMovieDao
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.mylist.data.local.MyListMovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.data.local.LastSearchDao
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.data.local.RegionDao
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.data.local.entities.LastSearchEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.data.local.entities.RegionEntity
import com.iusarican.data.database.VisitedMovieDao
import com.iusarican.data.model.local.VisitedMovieEntity

@Database(
    entities = [
        GenreEntity::class,
        MovieEntity::class,
        MyListMovieEntity::class,
        VisitedMovieEntity::class,
        RegionEntity::class,
        LastSearchEntity::class
    ],
    version = MovieDatabase.DATABASE_VERSION
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