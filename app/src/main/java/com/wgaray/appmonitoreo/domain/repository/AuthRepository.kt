package com.wgaray.appmonitoreo.domain.repository

import com.wgaray.appmonitoreo.domain.model.Usuario

interface AuthRepository {
    suspend fun register(name: String, email: String, password: String, rol: String): Result<Usuario>
    suspend fun login(email: String, password: String): Result<Usuario>
}