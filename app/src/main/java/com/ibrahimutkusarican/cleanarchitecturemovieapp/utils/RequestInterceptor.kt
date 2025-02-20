package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RequestInterceptor @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val language = runBlocking {
            dataStore.data.map { prefs ->
                prefs[LANGUAGE_KEY] ?: DEFAULT_LANGUAGE
            }.first()
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
        val LANGUAGE_KEY = stringPreferencesKey("language")
        const val DEFAULT_LANGUAGE = "en"
    }
}