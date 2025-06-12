package com.wgaray.appmonitoreo.ui.screens.salud

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wgaray.appmonitoreo.domain.model.*
import com.wgaray.appmonitoreo.domain.usecase.salud.ObtenerDatosSaludUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import java.time.LocalDateTime

@HiltViewModel
class SaludViewModel @Inject constructor(
    // Se inyecta pero no se usa por ahora
    private val obtenerDatosSaludUseCase: ObtenerDatosSaludUseCase
) : ViewModel() {

    private val _datos = MutableStateFlow<DatosSalud?>(null)
    val datos: StateFlow<DatosSalud?> = _datos

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        cargarDatosSimulados()
    }

    private fun cargarDatosSimulados() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val ahora = LocalDateTime.now()
                _datos.value = DatosSalud(
                    fecha = ahora,
                    frecuenciaCardiaca = FrecuenciaCardiaca(
                        valor = 78,
                        unidad = "bpm",
                        estado = "normal",
                        registradoEn = ahora.minusMinutes(10)
                    ),
                    presionArterial = PresionArterial(
                        sistolica = 115,
                        diastolica = 75,
                        unidad = "mmHg",
                        estado = "normal",
                        registradoEn = ahora.minusMinutes(20)
                    ),
                    pasos = Pasos(
                        cantidad = 3100,
                        metaDiaria = 6000,
                        estado = "bajo"
                    )
                )
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

}
