package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils

import com.ibrahimutkusarican.cleanarchitecturemovieapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    /// TODO: Add an language parameter from dataStore 
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newUrl = originalRequest.url.newBuilder()
            .addQueryParameter(LANGUAGE_PARAMETER, "en")  // Add your query parameter here
            .build()

        val requestWithAuthAndQuery = originalRequest.newBuilder()
            .url(newUrl)
            .addHeader(HEADER_KEY, HEADER_AUTH_TOKEN)
            .build()

        return chain.proceed(requestWithAuthAndQuery)
    }

    companion object {
        private const val HEADER_KEY = "Authorization"
        private const val HEADER_AUTH_TOKEN = "Bearer ${BuildConfig.TMDB_AUTH_TOKEN}"
        private const val LANGUAGE_PARAMETER = "language"
    }
}