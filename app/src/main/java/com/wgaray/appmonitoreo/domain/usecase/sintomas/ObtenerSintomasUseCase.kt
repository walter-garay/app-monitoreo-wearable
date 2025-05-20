package com.wgaray.appmonitoreo.domain.usecase.sintomas

import com.wgaray.appmonitoreo.domain.repository.SintomaRepository
import com.wgaray.appmonitoreo.data.model.SintomaResponse
import javax.inject.Inject

class ObtenerSintomasUseCase @Inject constructor(
    private val repository: SintomaRepository
) {
    suspend operator fun invoke(): List<SintomaResponse> = repository.obtenerSintomas()
}
