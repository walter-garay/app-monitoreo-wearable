package com.wgaray.appmonitoreo.data.model

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val user: UsuarioResponse
)

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val rol: String = "gestante"
)

data class RegisterResponse(
    val user: UsuarioResponse,
    val token: String
)