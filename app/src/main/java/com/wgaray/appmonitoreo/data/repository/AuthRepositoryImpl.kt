package com.wgaray.appmonitoreo.data.repository

import android.util.Log
import javax.inject.Inject
import com.wgaray.appmonitoreo.data.datasource.AuthApiService
import com.wgaray.appmonitoreo.data.model.RegisterRequest
import com.wgaray.appmonitoreo.data.model.LoginRequest
import com.wgaray.appmonitoreo.domain.model.Usuario
import com.wgaray.appmonitoreo.domain.repository.AuthRepository
import com.wgaray.appmonitoreo.data.model.FCMTokenRequest

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

    override suspend fun login(email: String, password: String): Result<Usuario> {
        return try {
            val response = api.login(LoginRequest(email, password))
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

    // Nuevo método para enviar el token FCM al servidor
    override suspend fun enviarTokenFCM(request: FCMTokenRequest): Result<Unit> {
        return try {
            // Realiza la llamada a la API para enviar el token FCM
            val response = api.enviarTokenFCM(request)

            // Verifica si la respuesta es exitosa (status 200 OK)
            if (response.isSuccessful) {
                Log.e("EXITO FCM", "Error al enviar token FCM al backend")

                Result.success(Unit)  // Si fue exitoso, devolvemos un resultado exitoso
            } else {
                // Si hubo algún error en la respuesta, lanzamos una excepción
                Log.e("EXITO FCM", "Error al enviar token FCM al backend111")
                Result.failure(Exception("Error al enviar el token FCM"))

            }
        } catch (e: Exception) {
            // Maneja cualquier excepción que pueda ocurrir durante la llamada a la API
            Log.e("EXITO FCM", "Error al enviar token FCM al backend222", e)
            Result.failure(Exception("Error al enviar el token FCM al servidor", e))
        }
    }


}
