package com.wgaray.appmonitoreo.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.wgaray.appmonitoreo.data.datasource.AuthApiService
import com.wgaray.appmonitoreo.data.datasource.AuthInterceptor
import com.wgaray.appmonitoreo.data.datasource.SaludApiService
import com.wgaray.appmonitoreo.data.datasource.SintomaApiService
import com.wgaray.appmonitoreo.data.local.SessionPreferencesDataSource
import com.wgaray.appmonitoreo.util.LocalDateTimeAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        sessionPreferencesDataSource: SessionPreferencesDataSource
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(sessionPreferencesDataSource))
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
            .create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService =
        retrofit.create(AuthApiService::class.java)

    @Provides
    @Singleton
    fun provideSintomaApiService(retrofit: Retrofit): SintomaApiService =
        retrofit.create(SintomaApiService::class.java)

    @Provides
    @Singleton
    fun provideSaludApiService(retrofit: Retrofit): SaludApiService =
        retrofit.create(SaludApiService::class.java)
}
