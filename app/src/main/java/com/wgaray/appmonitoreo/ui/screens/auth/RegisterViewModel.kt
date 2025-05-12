package com.wgaray.appmonitoreo.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wgaray.appmonitoreo.domain.model.Usuario
import com.wgaray.appmonitoreo.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val state: StateFlow<RegisterState> = _state

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            _state.value = RegisterState.Loading
            val result = registerUseCase(name, email, password)
            _state.value = if (result.isSuccess) {
                RegisterState.Success(result.getOrNull()!!)
            } else {
                RegisterState.Error(result.exceptionOrNull()?.message ?: "Error desconocido")
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
