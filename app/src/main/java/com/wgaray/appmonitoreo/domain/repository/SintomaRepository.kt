package com.wgaray.appmonitoreo.domain.repository

import com.wgaray.appmonitoreo.data.model.SintomaRequest
import com.wgaray.appmonitoreo.data.model.SintomaResponse

interface SintomaRepository {
    suspend fun registrarSintoma(request: SintomaRequest)
    suspend fun obtenerSintomas(): List<SintomaResponse>
}
