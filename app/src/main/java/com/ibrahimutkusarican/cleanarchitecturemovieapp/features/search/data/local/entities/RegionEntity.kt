package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "region")
data class RegionEntity(
    @PrimaryKey @ColumnInfo(name = "region_code") val regionCode : String,
    @ColumnInfo(name = "region_name") val regionName : String
)
