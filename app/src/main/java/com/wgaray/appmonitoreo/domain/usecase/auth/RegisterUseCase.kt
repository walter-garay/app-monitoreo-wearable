package com.wgaray.appmonitoreo.domain.usecase.auth

import com.wgaray.appmonitoreo.domain.model.Usuario
import com.wgaray.appmonitoreo.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(name: String, email: String, password: String): Result<Usuario> {
        return repository.register(name, email, password, rol = "gestante")
    }
}