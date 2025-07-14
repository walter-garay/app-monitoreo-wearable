// Verifica y solicita el permiso de notificaciones
package com.wgaray.appmonitoreo.domain.usecase.permissions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import javax.inject.Inject

class RequestNotificationPermissionUseCase @Inject constructor(
    private val context: Context
) {
    companion object {
        const val PERMISSION_REQUEST_CODE = 1001
    }

    // Verifica si el permiso ha sido concedido
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)  // Requiere Android 13 o superior
    fun isNotificationPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    }

    // Solicita el permiso para mostrar notificaciones
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)  // Requiere Android 13 o superior
    fun requestPermission(activity: ComponentActivity) {
        if (!isNotificationPermissionGranted()) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                PERMISSION_REQUEST_CODE
            )
        }
    }
}
