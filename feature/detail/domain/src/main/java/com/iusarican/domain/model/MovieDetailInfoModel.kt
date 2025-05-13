package com.iusarican.domain.model

import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.domain.model.MovieDetailModel


data class MovieDetailInfoModel(
    val movieId: Int,
    val title: String,
    val tagline: String,
    val posterImageUrl: String?,
    val backgroundImageUrl: String?,
    val releaseYear: String,
    val isAddedToWatchList: Boolean = false,
    val isFavorite : Boolean = false,
    val runtime: String,
    val genre: String,
)

val mockMovieDetailModel = MovieDetailModel(
    movieDetailInfoModel = MovieDetailInfoModel(
        movieId = 12345,
        title = "Mock Movie Title",
        tagline = "This is a mock tagline",
        posterImageUrl = "https://example.com/mock-poster.jpg",
        backgroundImageUrl = "https://example.com/mock-background.jpg",
        releaseYear = "2023",
        isAddedToWatchList = false,
        runtime = "120 minutes",
        genre = "Action",
        isFavorite = true
    ),
    movieDetailAboutModel = MovieDetailAboutModel(
        budget = "100,000,000",
        revenue = "300,000,000",
        status = "Released",
        genres = listOf("Action", "Drama"),
        rating = "6.2 / 10 (1.2K)",
        fullReleaseDate = "15 May 2023",
        overview = "This is a mock overview of the movie.",
        casts = listOf(
            MovieDetailCastModel(
                characterName = "John Wick",
                gender = 2,
                order = 1,
                originalName = "Keanu Reeves",
                profileImage = "https://example.com/mock-profile-1.jpg"
            ),
            MovieDetailCastModel(
                characterName = "Sofia",
                gender = 1,
                order = 2,
                originalName = "Halle Berry",
                profileImage = "https://example.com/mock-profile-2.jpg"
            )
        )
    ),
    movieDetailTrailerModel = MovieDetailTrailerModel(
        trailers = listOf(
            MovieDetailTrailerModelItem(
                name = "Movie Trail v1",
                key = "00"
            ),
            MovieDetailTrailerModelItem(
                name = "Movie Trail v2",
                key = "11"
            )
        )
    ),
    movieDetailReviewModel = MovieDetailReviewModel(
        reviews = listOf(
            MovieDetailReviewModelItem(
                authorName = "Reviewer One",
                review = "Amazing movie. Lots of action!",
                updateTime = "2023-05-01",
                rating = 8.5,
                authorNickName = "ActionFan",
                authorProfilePhoto = null
            ),
            MovieDetailReviewModelItem(
                authorName = "Reviewer Two",
                review = "A bit over the top, but fun overall.",
                updateTime = "2023-05-02",
                rating = 7.2,
                authorNickName = "CineLover",
                authorProfilePhoto = "https://example.com/mock-reviewer-2.jpg"
            )
        )
    ),
    movieDetailRecommendedMovies = MovieDetailRecommendedModel(
        listOf(
            MovieDetailRecommendedModelItem(
                movieId = 67890,
                movieTitle = "Movie Title 1",
                movieGenres = listOf("Action", "Thriller"),
                moviePosterImageUrl = "https://example.com/mock-recommendation-poster.jpg",
                movieTMDBScore = 6.9
            ),
            MovieDetailRecommendedModelItem(
                movieId = 67891,
                movieTitle = "Movie Title 2",
                movieGenres = listOf("Sport", "Thriller"),
                moviePosterImageUrl = "https://example.com/mock-recommendation-poster.jpg",
                movieTMDBScore = 6.9
            )
        )
    )
)

