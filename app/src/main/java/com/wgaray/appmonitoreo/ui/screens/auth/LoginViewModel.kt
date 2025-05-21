package com.wgaray.appmonitoreo.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wgaray.appmonitoreo.domain.usecase.auth.LoginUseCase
import com.wgaray.appmonitoreo.domain.usecase.session.SaveSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveSessionUseCase: SaveSessionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state: StateFlow<LoginState> = _state

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.value = LoginState.Loading
            val result = loginUseCase(email, password)
            if (result.isSuccess) {
                val usuario = result.getOrNull()!!
                saveSessionUseCase(usuario) // guardar sesión (token)
                _state.value = LoginState.Success(usuario)
            } else {
                _state.value = LoginState.Error(result.exceptionOrNull()?.message ?: "Error desconocido")
            }
        }
    }

    sealed class LoginState {
        object Idle : LoginState()
        object Loading : LoginState()
        data class Success(val usuario: com.wgaray.appmonitoreo.domain.model.Usuario) : LoginState()
        data class Error(val message: String) : LoginState()
    }
}
