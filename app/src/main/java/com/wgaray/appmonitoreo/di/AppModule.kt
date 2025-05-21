package com.wgaray.appmonitoreo.di

import android.content.Context
import com.wgaray.appmonitoreo.data.local.SessionPreferencesDataSource
import com.wgaray.appmonitoreo.data.repository.AuthRepositoryImpl
import com.wgaray.appmonitoreo.data.repository.SessionRepositoryImpl
import com.wgaray.appmonitoreo.domain.repository.AuthRepository
import com.wgaray.appmonitoreo.domain.repository.SessionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

import com.wgaray.appmonitoreo.data.datasource.SintomaApiService
import com.wgaray.appmonitoreo.data.repository.SintomaRepositoryImpl
import com.wgaray.appmonitoreo.domain.repository.SintomaRepository

import com.wgaray.appmonitoreo.domain.usecase.session.ClearSessionUseCase
import com.wgaray.appmonitoreo.domain.usecase.session.GetSessionUseCase
import com.wgaray.appmonitoreo.domain.usecase.session.SaveSessionUseCase
import com.wgaray.appmonitoreo.domain.usecase.sintomas.RegistrarSintomaUseCase
import com.wgaray.appmonitoreo.domain.usecase.sintomas.ObtenerSintomasUseCase


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // AUTH
    @Provides
    @Singleton
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl



    // SESSION
    @Provides
    @Singleton
    fun provideSessionPreferences(@ApplicationContext context: Context): SessionPreferencesDataSource {
        return SessionPreferencesDataSource(context)
    }

    @Provides
    @Singleton
    fun provideSessionRepository(
        local: SessionPreferencesDataSource
    ): SessionRepository = SessionRepositoryImpl(local)

    @Provides
    @Singleton
    fun provideSaveSessionUseCase(repository: SessionRepository): SaveSessionUseCase {
        return SaveSessionUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetSessionUseCase(repository: SessionRepository): GetSessionUseCase {
        return GetSessionUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideClearSessionUseCase(repository: SessionRepository): ClearSessionUseCase {
        return ClearSessionUseCase(repository)
    }


    // SÍNTOMAS
    @Provides
    @Singleton
    fun provideSintomaRepository(api: SintomaApiService): SintomaRepository {
        return SintomaRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideRegistrarSintomaUseCase(repository: SintomaRepository): RegistrarSintomaUseCase {
        return RegistrarSintomaUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideObtenerSintomasUseCase(repository: SintomaRepository): ObtenerSintomasUseCase {
        return ObtenerSintomasUseCase(repository)
    }


}
