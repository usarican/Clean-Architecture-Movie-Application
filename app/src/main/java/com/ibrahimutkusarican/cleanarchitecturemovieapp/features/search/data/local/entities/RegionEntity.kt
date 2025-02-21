package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "region")
data class RegionEntity(
    @ColumnInfo(name = "region_code") val regionCode : String,
    @ColumnInfo(name = "region_name") val regionName : String
)
