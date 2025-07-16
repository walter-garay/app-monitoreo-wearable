package com.wgaray.appmonitoreo.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wgaray.appmonitoreo.domain.model.Usuario
import com.wgaray.appmonitoreo.domain.usecase.session.ClearSessionUseCase
import com.wgaray.appmonitoreo.domain.usecase.session.GetSessionUseCase
import com.wgaray.appmonitoreo.domain.usecase.session.SaveSessionUseCase
import com.wgaray.appmonitoreo.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val saveSessionUseCase: SaveSessionUseCase,
    private val getSessionUseCase: GetSessionUseCase,
    private val clearSessionUseCase: ClearSessionUseCase,
    private val authRepository: AuthRepository
) : ViewModel() {

    val session: StateFlow<Usuario?> = getSessionUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun saveSession(user: Usuario) {
        viewModelScope.launch {
            saveSessionUseCase(user)
        }
    }

    private val _logoutState = MutableStateFlow<LogoutState>(LogoutState.Idle)
    val logoutState: StateFlow<LogoutState> = _logoutState

    fun logout() {
        viewModelScope.launch {
            _logoutState.value = LogoutState.Loading
            val result = authRepository.logout()
            _logoutState.value = if (result.isSuccess) LogoutState.Success else LogoutState.Error(result.exceptionOrNull()?.message ?: "Error desconocido")
        }
    }

    sealed class LogoutState {
        object Idle : LogoutState()
        object Loading : LogoutState()
        object Success : LogoutState()
        data class Error(val message: String) : LogoutState()
    }
}
