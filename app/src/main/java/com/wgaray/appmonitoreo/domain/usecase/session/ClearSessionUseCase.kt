package com.wgaray.appmonitoreo.domain.usecase.session

import com.wgaray.appmonitoreo.domain.repository.SessionRepository
import javax.inject.Inject

class ClearSessionUseCase @Inject constructor(
    private val repository: SessionRepository
) {
    suspend operator fun invoke() {
        repository.clearSession()
    }
}