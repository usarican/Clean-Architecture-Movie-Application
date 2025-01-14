package com.ibrahimutkusarican.cleanarchitecturemovieapp.core

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response.MovieResultResponse
import retrofit2.HttpException
import java.io.IOException

abstract class BasePagingSource : PagingSource<Int, MovieResultResponse>() {
    override fun getRefreshKey(state: PagingState<Int, MovieResultResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResultResponse> {
        return try {
            executeLoad(params)
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    abstract suspend fun executeLoad(params: LoadParams<Int>): LoadResult<Int, MovieResultResponse>
}