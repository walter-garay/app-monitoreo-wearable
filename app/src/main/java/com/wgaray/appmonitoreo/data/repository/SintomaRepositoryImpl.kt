package com.wgaray.appmonitoreo.data.repository

import com.wgaray.appmonitoreo.data.datasource.SintomaApiService
import com.wgaray.appmonitoreo.data.model.SintomaRequest
import com.wgaray.appmonitoreo.data.model.SintomaResponse
import com.wgaray.appmonitoreo.domain.repository.SintomaRepository
import javax.inject.Inject

class SintomaRepositoryImpl @Inject constructor(
    private val api: SintomaApiService
) : SintomaRepository {
    override suspend fun registrarSintoma(request: SintomaRequest) {
        api.registrarSintoma(request)
    }

    override suspend fun obtenerSintomas(): List<SintomaResponse> {
        return api.obtenerSintomas()
    }
}
