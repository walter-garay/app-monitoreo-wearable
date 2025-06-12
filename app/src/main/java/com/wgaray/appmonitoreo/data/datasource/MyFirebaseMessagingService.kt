package com.wgaray.appmonitoreo.data.datasource

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.wgaray.appmonitoreo.R
import com.wgaray.appmonitoreo.data.local.SessionPreferencesDataSource
import com.wgaray.appmonitoreo.data.model.FCMTokenRequest
import com.wgaray.appmonitoreo.data.repository.AuthRepositoryImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MyFirebaseMessagingService : FirebaseMessagingService() {

    @Inject
    lateinit var sessionPreferences: SessionPreferencesDataSource

    @Inject
    lateinit var authRepository: AuthRepositoryImpl

    companion object {
        private const val CHANNEL_ID = "alertas_salud"
        private const val CHANNEL_NAME = "Alertas de salud"
        private const val CHANNEL_DESC = "Canal para notificaciones de riesgo de preeclampsia"
        private const val TAG = "FCMService"
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "Nuevo token FCM generado: $token")

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val session = sessionPreferences.getSession()
                session?.let {
                    val request = FCMTokenRequest(token)
                    authRepository.enviarTokenFCM(request)
                    Log.d(TAG, "Token FCM enviado al servidor")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error al enviar token FCM al backend", e)
            }
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val title = remoteMessage.notification?.title ?: "Alerta de salud"
        val body = remoteMessage.notification?.body ?: "Se detectó una posible anomalía en tus signos vitales."

        mostrarNotificacion(title, body)
    }

    private fun mostrarNotificacion(title: String, message: String) {
        crearCanalNotificacion()

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_health_alert) // ícono personalizado
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(this).notify(System.currentTimeMillis().toInt(), notification)
    }

    private fun crearCanalNotificacion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val canal = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH).apply {
                description = CHANNEL_DESC
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(canal)
        }
    }
}
