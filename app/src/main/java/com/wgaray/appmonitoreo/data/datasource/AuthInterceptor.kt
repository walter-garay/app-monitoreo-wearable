package com.wgaray.appmonitoreo.data.datasource

import com.wgaray.appmonitoreo.data.local.SessionPreferencesDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val sessionPreferences: SessionPreferencesDataSource
) : Interceptor {

    private val excludedPaths = listOf("v1/login", "v1/register")

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val path = originalRequest.url.encodedPath

        // No agregar token para login y register
        if (excludedPaths.any { path.endsWith(it) }) {
            return chain.proceed(originalRequest)
        }

        val token = runBlocking {
            sessionPreferences.getSession().first()?.token
        }

        return if (token != null) {
            val newRequest = originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(newRequest)
        } else {
            chain.proceed(originalRequest)
        }
    }
}
