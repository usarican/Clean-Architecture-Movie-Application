package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.local.entity.GenreEntity

@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg genreEntity: GenreEntity)

    @Query("SELECT * FROM genres")
    suspend fun getGenres() : List<GenreEntity>


}