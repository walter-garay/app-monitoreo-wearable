package com.wgaray.appmonitoreo.di

import com.wgaray.appmonitoreo.data.repository.AuthRepositoryImpl
import com.wgaray.appmonitoreo.domain.repository.AuthRepository
import com.wgaray.appmonitoreo.domain.usecase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module // <-- ESTA ES LA ANOTACIÓN QUE TE FALTA
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    @Singleton
    fun provideRegisterUseCase(authRepository: AuthRepository): RegisterUseCase {
        return RegisterUseCase(authRepository)
    }
}
