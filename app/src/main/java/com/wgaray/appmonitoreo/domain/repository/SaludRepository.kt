package com.wgaray.appmonitoreo.domain.repository

import com.wgaray.appmonitoreo.data.model.SaludResponse

interface SaludRepository {
    suspend fun obtenerDatosSalud(): SaludResponse
}