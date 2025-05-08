package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.data.remote

import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.data.remote.responses.RegionResponse
import retrofit2.http.GET

interface RegionsService {
    @GET("watch/providers/regions")
    suspend fun getRegions() : RegionResponse
}