package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.remote

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.remote.responses.RegionResponse
import retrofit2.http.GET

interface RegionsService {
    @GET("watch/providers/regions")
    suspend fun getRegions() : RegionResponse
}