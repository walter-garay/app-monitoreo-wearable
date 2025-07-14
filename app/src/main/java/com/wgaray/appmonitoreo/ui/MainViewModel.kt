package com.wgaray.appmonitoreo.ui

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wgaray.appmonitoreo.domain.usecase.permissions.RequestNotificationPermissionUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class MainViewModel @Inject constructor(
    private val requestNotificationPermissionUseCase: RequestNotificationPermissionUseCase
) : ViewModel() {

    // Método para verificar y solicitar el permiso de notificaciones
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun checkAndRequestPermission(activity: ComponentActivity) {
        if (!requestNotificationPermissionUseCase.isNotificationPermissionGranted()) {
            // Si el permiso no está concedido, solicitarlo
            viewModelScope.launch {
                requestNotificationPermissionUseCase.requestPermission(activity)
            }
        }
    }
}
