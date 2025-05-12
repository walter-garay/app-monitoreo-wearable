package com.wgaray.appmonitoreo.data.datasource

import com.wgaray.appmonitoreo.data.model.RegisterRequest
import com.wgaray.appmonitoreo.data.model.RegisterResponse

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Headers

interface AuthApiService {
    @Headers("Accept: application/json")
    @POST("register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse
}
