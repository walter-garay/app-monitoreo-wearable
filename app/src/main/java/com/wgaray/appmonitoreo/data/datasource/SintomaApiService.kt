package com.wgaray.appmonitoreo.data.datasource

import com.wgaray.appmonitoreo.data.model.SintomaRequest
import com.wgaray.appmonitoreo.data.model.SintomaResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET


interface SintomaApiService {
    @POST("sintomas")
    suspend fun registrarSintoma(@Body sintoma: SintomaRequest)


    @GET("sintomas")
    suspend fun obtenerSintomas(): List<SintomaResponse>
}
