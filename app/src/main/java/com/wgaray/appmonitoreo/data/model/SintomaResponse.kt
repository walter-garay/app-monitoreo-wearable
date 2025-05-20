package com.wgaray.appmonitoreo.data.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class SintomaResponse(
    val id: Int,
    @SerializedName("hora_inicio")
    val hora_inicio: LocalDateTime,
    @SerializedName("hora_fin")
    val hora_fin: LocalDateTime?,
    val severidad: String,
    val descripcion: String
)
