package com.wgaray.appmonitoreo  // Paquete raíz, igual que MainActivity

import android.app.Application
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppMonitoreoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

        Firebase.messaging.token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                println("El token es: $token")
                return@addOnCompleteListener
            } else {
                println("El token no fue generado")
            }
        }

    }
}