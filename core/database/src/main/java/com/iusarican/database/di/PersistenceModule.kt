package com.iusarican.database.di

import android.content.Context
import androidx.room.Room
import com.iusarican.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Singleton
    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        val room = Room.databaseBuilder(
            context, MovieDatabase::class.java, "db-movie"
        )
        room.fallbackToDestructiveMigration()
        return room.build()
    }
}