package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.usecase.GetMovieGenresUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.data.MyListRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.mapper.MyListMovieModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model.MyListMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model.MyListUpdatePage
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extension.getSuccessOrThrow
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest

class DeleteMyListMovieUseCaseImpl @AssistedInject constructor(
    private val myListRepository: MyListRepository,
    private val myListMovieModelMapper: MyListMovieModelMapper,
    private val getMovieGenreUseCase: GetMovieGenresUseCase,
    @Assisted val myListMovieModel: MyListMovieModel,
    @Assisted val myListUpdatePage: MyListUpdatePage
) : DeleteMyListMovieUseCase, BaseUseCase() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun deleteMyListMovie(): Flow<UiState<Any>> {
        return getMovieGenreUseCase.getMovieGenresUseCase().flatMapLatest { genreListState ->
            execute {
                val genreList = genreListState.getSuccessOrThrow()
                val updatedMyListMovieModel =
                    myListUpdatePage.getUpdatedMovieModel(myListMovieModel)
                val movieEntity =
                    myListMovieModelMapper.modelToEntity(
                        updatedMyListMovieModel,
                        genreList
                    )
                if (myListUpdatePage.thisMovieModelShouldDeleteFromMyList(myListMovieModel)) {
                    myListRepository.deleteMyListMovie(movieEntity).first().getSuccessOrThrow()
                } else {
                    myListRepository.insertMyListMovie(movieEntity).first().getSuccessOrThrow()
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun undoDeleteMyListMovie(): Flow<UiState<Any>> {
        return getMovieGenreUseCase.getMovieGenresUseCase().flatMapLatest { genreListState ->
            execute {
                val genreList = genreListState.getSuccessOrThrow()
                val movieEntity =
                    myListMovieModelMapper.modelToEntity(
                        myListMovieModel,
                        genreList
                    )
                myListRepository.insertMyListMovie(movieEntity).first().getSuccessOrThrow()
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(myListMovieModel : MyListMovieModel, myListUpdatePage: MyListUpdatePage): DeleteMyListMovieUseCaseImpl
    }
}