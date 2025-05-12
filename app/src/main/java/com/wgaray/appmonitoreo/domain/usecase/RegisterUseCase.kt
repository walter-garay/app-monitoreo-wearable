package com.wgaray.appmonitoreo.domain.usecase

import javax.inject.Inject

import com.wgaray.appmonitoreo.domain.repository.AuthRepository
import com.wgaray.appmonitoreo.domain.model.Usuario

class RegisterUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(name: String, email: String, password: String): Result<Usuario> {
        return repository.register(name, email, password, rol = "gestante")
    }
}


