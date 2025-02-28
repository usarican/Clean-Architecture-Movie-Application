package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.local.entities.LastSearchEntity

@Dao
interface LastSearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchQuery(searchText: LastSearchEntity)

    @Query("SELECT * FROM last_search ORDER BY id DESC LIMIT 10")
    suspend fun getLastSearchQueries(): List<LastSearchEntity>

    @Query("DELETE FROM last_search WHERE id = (SELECT id FROM last_search ORDER BY id ASC LIMIT 1)")
    suspend fun deleteOldestSearchedText()

    @Query("SELECT COUNT(*) FROM last_search")
    suspend fun getSearchQueryCount(): Int

    @Query("DELETE FROM last_search")
    suspend fun deleteAllSearchQueries()

    @Delete
    suspend fun deleteSearchQuery(searchText: LastSearchEntity)

}