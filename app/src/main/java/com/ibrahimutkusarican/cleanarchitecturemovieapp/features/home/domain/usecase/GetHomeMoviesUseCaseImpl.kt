package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.usecase

import android.util.Log
import androidx.compose.ui.graphics.Color
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.MovieExceptions
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.usecase.GetMovieGenresUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.MovieRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.mapper.HomeMovieModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.HomeMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.CoilHelper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.MoviePosterSize
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.extractDominantColor
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.getSuccessOrThrow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetHomeMoviesUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieModelMapper: HomeMovieModelMapper,
    private val getMovieGenreUseCase: GetMovieGenresUseCase,
    private val coilHelper: CoilHelper
) : GetHomeMoviesUseCase {
    override fun getHomeMoviesUseCase(): Flow<UiState<Map<MovieType, List<HomeMovieModel>>>> {
        return combine(
            getMovieGenreUseCase.getMovieGenresUseCase(),
            movieRepository.getMoviesByType(MovieType.NOW_PLAYING, limit = 10),
            movieRepository.getMoviesByType(MovieType.POPULAR),
            movieRepository.getMoviesByType(MovieType.TOP_RATED),
            movieRepository.getMoviesByType(MovieType.UPCOMING)
        ) { stateGenres, stateNowPlaying, statePopular, stateTopRated, stateUpcoming ->
            UiState.Loading
            val genreModelList = stateGenres.getSuccessOrThrow()
            val nowPlayingList = stateNowPlaying.getSuccessOrThrow()
            val popularList = statePopular.getSuccessOrThrow()
            val topRatedList = stateTopRated.getSuccessOrThrow()
            val upcomingList = stateUpcoming.getSuccessOrThrow()

            val nowPlayingMovieModelList = nowPlayingList.map { movieEntity ->
                val nowPlayingHomeMovieModel = movieModelMapper.mapEntityToModel(
                    entity = movieEntity,
                    genreList = genreModelList,
                    posterSize = MoviePosterSize.W780
                )
                val dominantColor =
                    getMovieImageDominantColor(nowPlayingHomeMovieModel.moviePosterImageUrl)
                nowPlayingHomeMovieModel.copy(movieDominantColor = dominantColor)
            }
            val popularMovieModelList = popularList.map { movieEntity ->
                movieModelMapper.mapEntityToModel(
                    entity = movieEntity,
                    genreList = genreModelList
                )
            }
            val topRatedMovieModelList = topRatedList.map { movieEntity ->
                movieModelMapper.mapEntityToModel(
                    entity = movieEntity,
                    genreList = genreModelList
                )
            }
            val upComingMovieModelList = upcomingList.map { movieEntity ->
                movieModelMapper.mapEntityToModel(
                    entity = movieEntity,
                    genreList = genreModelList
                )
            }

            val movieMap = mapOf(
                MovieType.NOW_PLAYING to nowPlayingMovieModelList,
                MovieType.POPULAR to popularMovieModelList,
                MovieType.TOP_RATED to topRatedMovieModelList,
                MovieType.UPCOMING to upComingMovieModelList
            )
            UiState.Success(movieMap)
        }.catch { exp ->
            Log.d("UseCaseImp", exp.message.toString())
            UiState.Error(MovieExceptions.GeneralException(exp.message))
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun getMovieImageDominantColor(imageUrl: String?): Color? {
        val bitmap = coilHelper.loadBitmapFromImageUrl(imageUrl)
        return bitmap?.extractDominantColor()
    }
}