package com.wgaray.appmonitoreo.data.datasource

import com.wgaray.appmonitoreo.data.model.SaludResponse
import retrofit2.http.GET

interface SaludApiService {
    @GET("salud/actual")
    suspend fun obtenerDatosSalud(): SaludResponse
}
