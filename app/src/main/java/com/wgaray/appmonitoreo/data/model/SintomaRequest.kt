package com.wgaray.appmonitoreo.data.model

data class SintomaRequest(
    val hora_inicio: String,
    val hora_fin: String?,
    val severidad: String,
    val descripcion: String
)
