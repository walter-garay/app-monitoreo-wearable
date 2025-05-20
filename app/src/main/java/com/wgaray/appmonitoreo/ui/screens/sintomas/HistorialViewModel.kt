package com.wgaray.appmonitoreo.ui.screens.sintomas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wgaray.appmonitoreo.data.model.SintomaResponse
import com.wgaray.appmonitoreo.domain.usecase.sintomas.ObtenerSintomasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistorialViewModel @Inject constructor(
    private val obtenerSintomasUseCase: ObtenerSintomasUseCase
) : ViewModel() {

    private val _sintomas = MutableStateFlow<List<SintomaResponse>>(emptyList())
    val sintomas: StateFlow<List<SintomaResponse>> = _sintomas

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun cargarSintomas() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val lista = obtenerSintomasUseCase()
                _sintomas.value = lista
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
