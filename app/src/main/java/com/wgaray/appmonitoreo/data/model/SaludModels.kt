package com.wgaray.appmonitoreo.data.model

data class SaludResponse(
    val fecha: String,
    val datos_salud: DatosSaludDto
)

data class DatosSaludDto(
    val frecuencia_cardiaca: FrecuenciaCardiacaDto,
    val presion_arterial: PresionArterialDto,
    val pasos: PasosDto
)

data class FrecuenciaCardiacaDto(val valor: Int, val unidad: String, val estado: String, val registrado_en: String)
data class PresionArterialDto(val sistolica: Int, val diastolica: Int, val unidad: String, val estado: String, val registrado_en: String)
data class PasosDto(val cantidad: Int, val meta_diaria: Int, val estado: String)
