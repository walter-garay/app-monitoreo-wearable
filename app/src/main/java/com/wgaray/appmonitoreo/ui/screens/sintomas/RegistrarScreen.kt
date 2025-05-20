package com.wgaray.appmonitoreo.ui.screens.sintomas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wgaray.appmonitoreo.data.model.SintomaRequest
import java.time.LocalDateTime

@Composable
fun RegistrarScreen(
    navController: NavController,
    viewModel: SintomasViewModel = hiltViewModel()
) {
    var descripcion by remember { mutableStateOf("") }
    var severidad by remember { mutableStateOf("leve") }

    val severidades = listOf("leve", "moderado", "grave")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Registrar Síntoma",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = false,
            maxLines = 4
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Severidad", style = MaterialTheme.typography.labelLarge)

        Column {
            severidades.forEach { nivel ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = severidad == nivel,
                        onClick = { severidad = nivel }
                    )
                    Text(text = nivel.replaceFirstChar { it.uppercase() })
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                val request = SintomaRequest(
                    hora_inicio = LocalDateTime.now().toString(),
                    hora_fin = null,
                    severidad = severidad,
                    descripcion = descripcion
                )
                viewModel.registrarSintoma(
                    request,
                    onSuccess = {
                        descripcion = ""
                        severidad = "leve"
                        // Redirigir al historial de síntomas al guardar correctamente
                        navController.navigate("historial_sintomas") {
                            popUpTo("sintomas") { inclusive = true }
                        }
                    },
                    onError = {
                        // Aquí puedes mostrar un Snackbar o Toast con el error
                    }
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }
    }
}
