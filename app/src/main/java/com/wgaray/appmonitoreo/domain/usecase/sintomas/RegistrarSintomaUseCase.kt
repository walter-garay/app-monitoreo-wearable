package com.wgaray.appmonitoreo.domain.usecase.sintomas

import com.wgaray.appmonitoreo.data.model.SintomaRequest
import com.wgaray.appmonitoreo.domain.repository.SintomaRepository
import javax.inject.Inject

class RegistrarSintomaUseCase @Inject constructor(
    private val repository: SintomaRepository
) {
    suspend operator fun invoke(request: SintomaRequest) = repository.registrarSintoma(request)
}
