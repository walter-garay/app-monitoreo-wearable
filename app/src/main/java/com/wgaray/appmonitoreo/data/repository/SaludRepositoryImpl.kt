package com.wgaray.appmonitoreo.data.repository

import com.wgaray.appmonitoreo.data.datasource.SaludApiService
import com.wgaray.appmonitoreo.data.model.SaludResponse
import com.wgaray.appmonitoreo.domain.repository.SaludRepository
import javax.inject.Inject

class SaludRepositoryImpl @Inject constructor(
    private val api: SaludApiService
) : SaludRepository {
    override suspend fun obtenerDatosSalud(): SaludResponse {
        return api.obtenerDatosSalud()
    }
}
