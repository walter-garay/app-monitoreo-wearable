package com.wgaray.appmonitoreo.domain.model

data class Sintoma(
    val horaInicio: String,
    val horaFin: String?,
    val severidad: String,
    val descripcion: String
)
