package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.BasicMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.data.MyListRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.mapper.MyListMovieModelMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteMyListMovieUseCaseImpl @Inject constructor(
    private val myListRepository: MyListRepository,
    private val myListMovieModelMapper: MyListMovieModelMapper
) : BaseUseCase(), DeleteMyListMovieUseCase {
    override fun deleteMyListMovieFromDetail(movieDetailModel: MovieDetailModel): Flow<UiState<Unit>> {
        return execute {
            myListRepository.deleteMyListMovie(
                myListMovieModelMapper.movieDetailModelToMyListMovieEntity(
                    movieDetailModel
                )
            )
        }
    }

    override fun deleteMyListMovieFromHome(basicMovieModel: BasicMovieModel): Flow<UiState<Unit>> {
        return execute {
            myListRepository.deleteMyListMovie(
                myListMovieModelMapper.basicMovieModelToMyListMovieEntity(
                    basicMovieModel
                )
            )
        }
    }
}