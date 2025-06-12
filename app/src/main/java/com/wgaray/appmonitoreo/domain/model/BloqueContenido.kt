package com.wgaray.appmonitoreo.domain.model

import androidx.annotation.DrawableRes

sealed class BloqueContenido {
    data class Parrafo(val texto: String) : BloqueContenido()
    data class Subtitulo(val texto: String) : BloqueContenido()
    data class Imagen(@DrawableRes val resId: Int) : BloqueContenido()
    data class Lista(val items: List<String>) : BloqueContenido()
}
