package com.wgaray.appmonitoreo.ui.screens.sintomas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wgaray.appmonitoreo.data.model.SintomaRequest
import com.wgaray.appmonitoreo.domain.usecase.sintomas.RegistrarSintomaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SintomasViewModel @Inject constructor(
    private val registrarSintomaUseCase: RegistrarSintomaUseCase
) : ViewModel() {

    fun registrarSintoma(
        request: SintomaRequest,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                registrarSintomaUseCase(request)
                onSuccess()
            } catch (e: Exception) {
                onError(e.localizedMessage ?: "Error al registrar síntoma")
            }
        }
    }
}
