package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.data.local

import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.database.VisitedMovieDao
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.model.local.VisitedMovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.data.local.entities.LastSearchEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.data.local.entities.RegionEntity
import javax.inject.Inject

class SearchLocalDataSource @Inject constructor(
    private val visitedMovieDao: VisitedMovieDao,
    private val regionDao: RegionDao,
    private val lastSearchDao: LastSearchDao
) {

    suspend fun getLastVisitedMovies(): List<VisitedMovieEntity> =
        visitedMovieDao.getVisitedMovies()

    suspend fun getRegions(): List<RegionEntity> = regionDao.getRegions()

    suspend fun insertRegions(regions: List<RegionEntity>) = regionDao.insertRegions(regions)

    suspend fun deleteAllRegions() = regionDao.deleteAllRegions()

    suspend fun getLastSearchQueries(): List<LastSearchEntity> =
        lastSearchDao.getLastSearchQueries()

    suspend fun insertSearchQuery(searchText: LastSearchEntity) {
        lastSearchDao.insertSearchQuery(searchText)
        val count = lastSearchDao.getSearchQueryCount()
        if (count > 10) {
            lastSearchDao.deleteOldestSearchedText()
        }
    }

    suspend fun deleteAllSearchQueries() = lastSearchDao.deleteAllSearchQueries()

    suspend fun deleteSearchQuery(searchText: LastSearchEntity) =
        lastSearchDao.deleteSearchQuery(searchText)

}