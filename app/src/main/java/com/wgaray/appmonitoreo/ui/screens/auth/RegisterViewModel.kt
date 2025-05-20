package com.wgaray.appmonitoreo.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wgaray.appmonitoreo.domain.model.Usuario
import com.wgaray.appmonitoreo.domain.usecase.auth.RegisterUseCase
import com.wgaray.appmonitoreo.domain.usecase.session.SaveSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val saveSessionUseCase: SaveSessionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val state: StateFlow<RegisterState> = _state

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            _state.value = RegisterState.Loading
            val result = registerUseCase(name, email, password)
            if (result.isSuccess) {
                val usuario = result.getOrNull()!!
                saveSessionUseCase(usuario) // <-- Guardar sesión
                _state.value = RegisterState.Success(usuario)
            } else {
                _state.value = RegisterState.Error(result.exceptionOrNull()?.message ?: "Error desconocido")
            }
        }
    }

    sealed class RegisterState {
        object Idle : RegisterState()
        object Loading : RegisterState()
        data class Success(val usuario: Usuario) : RegisterState()
        data class Error(val message: String) : RegisterState()
    }
}
