package com.wgaray.appmonitoreo.data.repository

import javax.inject.Inject

import com.wgaray.appmonitoreo.data.datasource.AuthApiService
import com.wgaray.appmonitoreo.data.model.RegisterRequest
import com.wgaray.appmonitoreo.domain.model.Usuario
import com.wgaray.appmonitoreo.domain.repository.AuthRepository


class AuthRepositoryImpl @Inject constructor(private val api: AuthApiService) : AuthRepository {
    override suspend fun register(name: String, email: String, password: String, rol: String): Result<Usuario> {
        return try {
            val response = api.register(
                RegisterRequest(name, email, password, rol)
            )
            Result.success(
                Usuario(
                    id = response.user.id,
                    name = response.user.name,
                    email = response.user.email,
                    rol = response.user.rol,
                    token = response.token
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


}

