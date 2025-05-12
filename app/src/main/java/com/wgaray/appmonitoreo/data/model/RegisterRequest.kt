package com.wgaray.appmonitoreo.data.model

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val rol: String = "gestante"
)
