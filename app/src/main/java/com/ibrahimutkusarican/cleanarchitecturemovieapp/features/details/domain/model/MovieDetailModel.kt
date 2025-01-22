package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.model.GenreModel

data class MovieDetailModel(
    val movieId: Int,
    val title: String,
    val overview: String,
    val tagline: String,
    val posterImageUrl: String?,
    val backgroundImageUrl: String?,
    val releaseYear: String,
    val voteAverage: Double,
    val voteCount: Int,
    val genre: String,
    val isFavorite: Boolean,
    val isAddedToWatchList: Boolean,
    val budget: String?,
    val revenue: String?,
    val runtime: String,
    val status: String?
)

val mockMovieDetail = MovieDetailModel(
    movieId = 123,
    title = "Inception",
    overview = "A skilled thief is offered a chance at redemption if he can successfully plant an idea into a target's subconscious.",
    tagline = "Your mind is the scene of the crime.Your mind is the scene of the crime.Your mind is the scene of the crime.Your mind is the scene of the crime.",
    posterImageUrl = "https://example.com/posters/inception.jpg",
    backgroundImageUrl = "https://example.com/backdrops/inception_bg.jpg",
    releaseYear = "2010",
    voteAverage = 8.8,
    voteCount = 32000,
    genre = "Action",
    isFavorite = false,
    isAddedToWatchList = true,
    budget = "160000000",
    revenue = "829895144",
    runtime = "2h 20m",
    status = "Released"
)
