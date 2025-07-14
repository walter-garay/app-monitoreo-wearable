package com.wgaray.appmonitoreo.domain.model

import java.time.LocalDateTime

data class DatosSalud(
    val fecha: LocalDateTime,
    val frecuenciaCardiaca: FrecuenciaCardiaca,
    val presionArterial: PresionArterial,
    val pasos: Pasos
)

data class FrecuenciaCardiaca(val valor: Int, val unidad: String, val estado: String, val registradoEn: LocalDateTime)
data class PresionArterial(val sistolica: Int, val diastolica: Int, val unidad: String, val estado: String, val registradoEn: LocalDateTime)
data class Pasos(val cantidad: Int, val metaDiaria: Int, val estado: String)

