package com.wgaray.appmonitoreo.domain.model

import androidx.annotation.DrawableRes

sealed class BloqueContenido {
    data class Parrafo(val texto: String) : BloqueContenido()
    data class Subtitulo(val texto: String) : BloqueContenido()
    data class Imagen(@DrawableRes val resId: Int) : BloqueContenido()
    data class Lista(val items: List<String>) : BloqueContenido()
    data class Cita(val texto: String) : BloqueContenido() // Frase destacada
    data class Alerta(val texto: String) : BloqueContenido() // Bloque de advertencia o tip
    data class Video(val url: String) : BloqueContenido() // Video educativo (placeholder)
    object Separador : BloqueContenido() // Línea o espacio visual
}
