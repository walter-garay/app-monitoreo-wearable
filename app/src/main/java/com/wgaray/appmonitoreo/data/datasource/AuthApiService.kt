package com.wgaray.appmonitoreo.data.datasource

import com.wgaray.appmonitoreo.data.model.FCMTokenRequest
import com.wgaray.appmonitoreo.data.model.LoginRequest
import com.wgaray.appmonitoreo.data.model.LoginResponse
import com.wgaray.appmonitoreo.data.model.RegisterRequest
import com.wgaray.appmonitoreo.data.model.RegisterResponse
import retrofit2.Response

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Headers

interface AuthApiService {
    @Headers("Accept: application/json")
    @POST("register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("fcm/token")
    suspend fun enviarTokenFCM(@Body request: FCMTokenRequest): Response<Unit>

    @POST("logout")
    suspend fun logout(): Response<Unit>
}
