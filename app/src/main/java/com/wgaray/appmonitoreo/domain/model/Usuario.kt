package com.wgaray.appmonitoreo.domain.model

data class Usuario(
    val id: Int,
    val name: String,
    val email: String,
    val rol: String,
    val token: String? = null
)