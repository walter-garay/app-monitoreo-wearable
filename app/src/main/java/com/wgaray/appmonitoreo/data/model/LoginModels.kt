package com.wgaray.appmonitoreo.data.model

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val user: UsuarioResponse
)
