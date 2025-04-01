package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.feature.genre.domain.usecase.GetMovieGenresUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.data.MyListRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.mapper.MyListMovieModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model.MyListMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model.MyListPage
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extension.getSuccessOrThrow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMyListMovieUseCaseImpl @Inject constructor(
    private val myListRepository: MyListRepository,
    private val myListMovieModelMapper: MyListMovieModelMapper,
    private val getMovieGenreUseCase: GetMovieGenresUseCase
) : GetMyListMovieUseCase {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getMyListMovieUseCase(page: MyListPage): Flow<PagingData<MyListMovieModel>> {
        return getMovieGenreUseCase.getMovieGenresUseCase().flatMapLatest { genreState ->
            val genreList = genreState.getSuccessOrThrow()
            val pagingData = when (page) {
                MyListPage.FAVORITE -> myListRepository.getFavoriteMoviesPaging()
                MyListPage.WATCH_LIST -> myListRepository.getWatchListMoviesPaging()
            }
            pagingData.map { paging ->
                paging.map { entity ->
                    myListMovieModelMapper.entityToModel(
                        entity = entity, genreList = genreList
                    )
                }
            }
        }
    }
}