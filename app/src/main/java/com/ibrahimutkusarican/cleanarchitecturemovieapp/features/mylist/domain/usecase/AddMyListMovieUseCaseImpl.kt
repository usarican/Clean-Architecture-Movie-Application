package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.usecase.GetMovieGenresUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.BasicMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.data.MyListRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.mapper.MyListMovieModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.getSuccessOrThrow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class AddMyListMovieUseCaseImpl @Inject constructor(
    private val myListRepository: MyListRepository,
    private val myListMovieModelMapper: MyListMovieModelMapper,
    private val getMovieGenreUseCase: GetMovieGenresUseCase
) : BaseUseCase(), AddMyListMovieUseCase {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun addMyListMovieFromDetail(movieDetailModel: MovieDetailModel): Flow<UiState<Unit>> {
        return execute {
            getMovieGenreUseCase.getMovieGenresUseCase().flatMapLatest { genreListState ->
                val genreList = genreListState.getSuccessOrThrow()
                val movieEntity =
                    myListMovieModelMapper.movieDetailModelToMyListMovieEntity(
                        movieDetailModel,
                        genreList
                    )
                myListRepository.insertMyListMovie(movieEntity)
            }.first()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun addMyListMovieFromHome(basicMovieModel: BasicMovieModel): Flow<UiState<Unit>> {
        return execute {
            getMovieGenreUseCase.getMovieGenresUseCase().flatMapLatest { genreListState ->
                val genreList = genreListState.getSuccessOrThrow()
                val movieEntity =
                    myListMovieModelMapper.basicMovieModelToMyListMovieEntity(
                        basicMovieModel,
                        genreList
                    )
                myListRepository.insertMyListMovie(movieEntity)
            }.first()
        }
    }
}