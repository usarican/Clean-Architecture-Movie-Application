package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.usecase

import androidx.paging.PagingData
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model.MyListMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model.MyListPage
import kotlinx.coroutines.flow.Flow

interface GetMyListMovieUseCase {
    fun getMyListMovieUseCase(page: MyListPage) : Flow<PagingData<MyListMovieModel>>
}