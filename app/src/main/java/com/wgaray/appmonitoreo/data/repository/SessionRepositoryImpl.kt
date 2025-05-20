package com.wgaray.appmonitoreo.data.repository

import javax.inject.Inject

import com.wgaray.appmonitoreo.data.local.SessionPreferencesDataSource
import com.wgaray.appmonitoreo.domain.model.Usuario
import com.wgaray.appmonitoreo.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow


class SessionRepositoryImpl @Inject constructor(
    private val local: SessionPreferencesDataSource
) : SessionRepository {

    override suspend fun saveSession(user: Usuario) {
        local.saveSession(user)
    }

    override fun getSession(): Flow<Usuario?> {
        return local.getSession()
    }

    override suspend fun clearSession() {
        local.clearSession()
    }
}

