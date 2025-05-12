package com.iusarican.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iusarican.data.local.entity.GenreEntity

@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(genreList : List<GenreEntity>)

    @Query("SELECT * FROM genres")
    suspend fun getGenres() : List<GenreEntity>

    @Query("DELETE FROM genres")
    suspend fun deleteAllGenres()

}