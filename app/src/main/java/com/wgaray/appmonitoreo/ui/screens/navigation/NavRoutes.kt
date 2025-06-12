package com.wgaray.appmonitoreo.ui.screens.navigation

object NavRoutes {
    const val Register = "register"
    const val Login = "login"

    const val Main = "main"

    const val Educacion = "educacion"
    const val Sintomas = "sintomas"
    const val HistorialSintomas = "historial_sintomas"

    const val DetallePasoBase = "detalle"

    fun detallePasoRoute(pasoId: Int): String = "$DetallePasoBase/$pasoId"
    const val DetallePasoConArg = "$DetallePasoBase/{pasoId}"

    const val Salud = "salud"

}
