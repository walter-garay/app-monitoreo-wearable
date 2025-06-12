package com.wgaray.appmonitoreo.domain.usecase.salud

import com.wgaray.appmonitoreo.domain.repository.SaludRepository
import com.wgaray.appmonitoreo.data.model.SaludResponse
import javax.inject.Inject

class ObtenerDatosSaludUseCase @Inject constructor(
    private val repository: SaludRepository
) {
    suspend operator fun invoke(): SaludResponse {
        return repository.obtenerDatosSalud()
    }
}
