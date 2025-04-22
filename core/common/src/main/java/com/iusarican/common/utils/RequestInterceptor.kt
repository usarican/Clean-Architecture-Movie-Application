package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils

import androidx.datastore.preferences.core.stringPreferencesKey
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.data.UserSettingsDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RequestInterceptor @Inject constructor(
    private val userSettingsDataStore: UserSettingsDataStore
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val language = runBlocking {
            userSettingsDataStore.getLanguageCode().first()
        }

        val newUrl = originalRequest.url.newBuilder()
            .addQueryParameter(LANGUAGE_PARAMETER, language)
            .build()

        val requestWithAuthAndQuery = originalRequest.newBuilder()
            .url(newUrl)
            .addHeader(HEADER_KEY, HEADER_AUTH_TOKEN)
            .build()

        return chain.proceed(requestWithAuthAndQuery)
    }

    companion object {
        private const val HEADER_KEY = "Authorization"
        private const val HEADER_AUTH_TOKEN = "Bearer ${Constants.TMDB_AUTH_TOKEN}"
        private const val LANGUAGE_PARAMETER = "language"
    }
}