package com.wgaray.appmonitoreo.domain.repository

import com.wgaray.appmonitoreo.domain.model.Usuario
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    suspend fun saveSession(user: Usuario)
    fun getSession(): Flow<Usuario?>
    suspend fun clearSession()
}
