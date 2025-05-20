package com.wgaray.appmonitoreo.domain.usecase.session

import com.wgaray.appmonitoreo.domain.model.Usuario
import com.wgaray.appmonitoreo.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSessionUseCase @Inject constructor(
    private val repository: SessionRepository
) {
    operator fun invoke(): Flow<Usuario?> {
        return repository.getSession()
    }
}