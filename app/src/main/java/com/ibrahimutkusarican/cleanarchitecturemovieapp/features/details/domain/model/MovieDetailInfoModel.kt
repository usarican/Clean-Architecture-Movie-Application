package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model


data class MovieDetailInfoModel(
    val movieId: Int,
    val title: String,
    val tagline: String,
    val posterImageUrl: String?,
    val backgroundImageUrl: String?,
    val releaseYear: String,
    val isAddedToWatchList: Boolean,
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
        genre = "Action"
    ),
    movieDetailAboutModel = MovieDetailAboutModel(
        budget = "100,000,000",
        revenue = "300,000,000",
        status = "Released",
        genres = listOf("Action", "Drama"),
        voteCount = 2000,
        voteAverage = 7.8,
        fullReleaseDate = "15 May 2023",
        casts = listOf(
            CastModel(
                characterName = "John Wick",
                gender = 2,
                order = 1,
                originalName = "Keanu Reeves",
                profileImage = "https://example.com/mock-profile-1.jpg"
            ),
            CastModel(
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
            TrailerModel(
                name = "videoName",
                key = ""
            )
        )
    ),
    movieDetailReviewModel = MovieDetailReviewModel(
        reviews = listOf(
            AuthorModel(
                authorName = "Reviewer One",
                review = "Amazing movie. Lots of action!",
                updateTime = "2023-05-01",
                rating = 8.5,
                authorNickName = "ActionFan",
                authorProfilePhoto = null
            ),
            AuthorModel(
                authorName = "Reviewer Two",
                review = "A bit over the top, but fun overall.",
                updateTime = "2023-05-02",
                rating = 7.2,
                authorNickName = "CineLover",
                authorProfilePhoto = "https://example.com/mock-reviewer-2.jpg"
            )
        )
    ),
    movieDetailRecommendedMovies = MovieDetailRecommendedMovieModel(
        listOf(
            RecommendedMovieModel(
                movieId = 67890,
                movieTitle = "Recommended Action Flick",
                movieGenres = listOf("Action", "Thriller"),
                moviePosterImageUrl = "https://example.com/mock-recommendation-poster.jpg",
                movieTMDBScore = 6.9
            )
        )
    )
)

