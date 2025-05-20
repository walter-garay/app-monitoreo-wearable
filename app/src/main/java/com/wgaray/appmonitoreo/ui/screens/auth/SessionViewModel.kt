package com.wgaray.appmonitoreo.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wgaray.appmonitoreo.domain.model.Usuario
import com.wgaray.appmonitoreo.domain.usecase.session.ClearSessionUseCase
import com.wgaray.appmonitoreo.domain.usecase.session.GetSessionUseCase
import com.wgaray.appmonitoreo.domain.usecase.session.SaveSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val saveSessionUseCase: SaveSessionUseCase,
    private val getSessionUseCase: GetSessionUseCase,
    private val clearSessionUseCase: ClearSessionUseCase
) : ViewModel() {

    val session: StateFlow<Usuario?> = getSessionUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun saveSession(user: Usuario) {
        viewModelScope.launch {
            saveSessionUseCase(user)
        }
    }

    fun logout() {
        viewModelScope.launch {
            clearSessionUseCase()
        }
    }
}
