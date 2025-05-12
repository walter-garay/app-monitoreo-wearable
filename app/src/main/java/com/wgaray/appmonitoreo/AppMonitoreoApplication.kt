package com.wgaray.appmonitoreo  // Paquete raíz, igual que MainActivity

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppMonitoreoApplication : Application() {
    // Opcional: Configuraciones iniciales globales
}