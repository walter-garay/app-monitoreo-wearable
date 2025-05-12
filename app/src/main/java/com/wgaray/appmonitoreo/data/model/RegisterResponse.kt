package com.wgaray.appmonitoreo.data.model

import com.wgaray.appmonitoreo.data.model.UsuarioResponse

data class RegisterResponse(
    val user: UsuarioResponse,
    val token: String
)