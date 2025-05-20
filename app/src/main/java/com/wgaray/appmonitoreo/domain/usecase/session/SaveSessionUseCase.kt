package com.wgaray.appmonitoreo.domain.usecase.session

import com.wgaray.appmonitoreo.domain.model.Usuario
import com.wgaray.appmonitoreo.domain.repository.SessionRepository
import javax.inject.Inject

class SaveSessionUseCase @Inject constructor(
    private val repository: SessionRepository
) {
    suspend operator fun invoke(user: Usuario) {
        repository.saveSession(user)
    }
}