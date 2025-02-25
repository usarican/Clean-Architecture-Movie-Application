package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.local.entities.RegionEntity

@Dao
interface RegionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRegions(regions: List<RegionEntity>)

    @Query("SELECT * FROM region")
    suspend fun getRegions(): List<RegionEntity>

    @Query("DELETE FROM region")
    suspend fun deleteAllRegions()
}