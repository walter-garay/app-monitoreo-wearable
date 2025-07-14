package com.wgaray.appmonitoreo.domain.repository

import com.wgaray.appmonitoreo.domain.model.Usuario
import com.wgaray.appmonitoreo.data.model.FCMTokenRequest

interface AuthRepository {
    suspend fun register(name: String, email: String, password: String, rol: String): Result<Usuario>
    suspend fun login(email: String, password: String): Result<Usuario>
    suspend fun enviarTokenFCM(request: FCMTokenRequest): Result<Unit>
}