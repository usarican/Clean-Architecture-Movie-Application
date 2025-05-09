package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "last_search")
data class LastSearchEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "search_query") val searchText: String,
)
