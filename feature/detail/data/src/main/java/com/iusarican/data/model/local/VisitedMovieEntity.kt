package com.iusarican.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iusarican.data.remote.response.Genre

@Entity(tableName = "visited_movie")
data class VisitedMovieEntity(
    @PrimaryKey @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "poster_path") val posterPath: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "vote_average") val voteAverage: Double,
    @ColumnInfo(name = "vote_count") val voteCount: Int,
    @ColumnInfo(name = "adult") val adult: Boolean,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String,
    @ColumnInfo(name = "budget") val budget: Int,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "revenue") val revenue: Int,
    @ColumnInfo(name = "runtime") val runtime: Int,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "tagline") val tagline: String,
    @ColumnInfo(name = "genres") val genres: List<Genre>,
    @ColumnInfo(name = "visited_time") val visitedTime: Long = System.currentTimeMillis()
)