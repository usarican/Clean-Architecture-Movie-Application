package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.usecase.GetMovieGenresUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model.MovieDetailModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.BasicMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.data.MyListRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.mapper.MyListMovieModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model.MyListUpdatePage
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.getSuccessOrThrow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class UpdateMyListMovieUseCaseImpl @Inject constructor(
    private val myListRepository: MyListRepository,
    private val myListMovieModelMapper: MyListMovieModelMapper,
    private val getMovieGenreUseCase: GetMovieGenresUseCase
) : BaseUseCase(), UpdateMyListMovieUseCase {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun updateMyListMovieFromHome(
        basicMovieModel: BasicMovieModel,
        myListUpdatePage: MyListUpdatePage
    ): Flow<UiState<BasicMovieModel>> {
        return getMovieGenreUseCase.getMovieGenresUseCase().flatMapLatest { genreListState ->
            execute {
                val genreList = genreListState.getSuccessOrThrow()
                val updatedBasicMovieModel = myListUpdatePage.getUpdatedMovieModel(basicMovieModel)
                val movieEntity =
                    myListMovieModelMapper.basicMovieModelToMyListMovieEntity(
                        updatedBasicMovieModel,
                        genreList
                    )
                if (myListUpdatePage.thisMovieModelShouldDeleteFromMyList(basicMovieModel)) {
                    myListRepository.deleteMyListMovie(movieEntity).first().getSuccessOrThrow()
                } else {
                    myListRepository.insertMyListMovie(movieEntity).first().getSuccessOrThrow()
                }
                updatedBasicMovieModel
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun updateMyListMovieFromDetail(
        movieDetailModel: MovieDetailModel,
        myListUpdatePage: MyListUpdatePage
    ): Flow<UiState<MovieDetailModel>> {
        return getMovieGenreUseCase.getMovieGenresUseCase().flatMapLatest { genreListState ->
            execute {
                val genreList = genreListState.getSuccessOrThrow()
                val updatedMovieDetailModel = myListUpdatePage.getUpdatedMovieModel(movieDetailModel)
                val movieEntity =
                    myListMovieModelMapper.movieDetailModelToMyListMovieEntity(
                        updatedMovieDetailModel,
                        genreList
                    )
                if (myListUpdatePage.thisMovieModelShouldDeleteFromMyList(movieDetailModel)) {
                    myListRepository.deleteMyListMovie(movieEntity).first().getSuccessOrThrow()
                } else {
                    myListRepository.insertMyListMovie(movieEntity).first().getSuccessOrThrow()
                }
                updatedMovieDetailModel
            }
        }
    }
}