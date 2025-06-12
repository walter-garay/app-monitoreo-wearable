package com.wgaray.appmonitoreo.ui.screens.sintomas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wgaray.appmonitoreo.data.model.SintomaRequest
import com.wgaray.appmonitoreo.domain.usecase.sintomas.RegistrarSintomaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SintomasViewModel @Inject constructor(
    private val registrarSintomaUseCase: RegistrarSintomaUseCase
) : ViewModel() {

    private val _registrarSintomaState = MutableStateFlow<RegistrarSintomaState>(RegistrarSintomaState.Idle)
    val registrarSintomaState: StateFlow<RegistrarSintomaState> = _registrarSintomaState

    fun registrarSintoma(request: SintomaRequest) {
        viewModelScope.launch {
            _registrarSintomaState.value = RegistrarSintomaState.Loading
            try {
                registrarSintomaUseCase(request)
                _registrarSintomaState.value = RegistrarSintomaState.Success
            } catch (e: Exception) {
                _registrarSintomaState.value = RegistrarSintomaState.Error(e.localizedMessage ?: "Error al registrar síntoma")
            }
        }
    }

    sealed class RegistrarSintomaState {
        object Idle : RegistrarSintomaState()
        object Loading : RegistrarSintomaState()
        object Success : RegistrarSintomaState()
        data class Error(val message: String) : RegistrarSintomaState()
    }
}
