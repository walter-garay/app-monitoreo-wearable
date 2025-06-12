package com.wgaray.appmonitoreo.ui.screens.sintomas

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NoteAdd
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wgaray.appmonitoreo.data.model.SintomaRequest
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import com.wgaray.appmonitoreo.ui.theme.AppMonitoreoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrarScreen(
    navController: NavController,
    viewModel: SintomasViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val state by viewModel.registrarSintomaState.collectAsState()

    var descripcion by remember { mutableStateOf("") }
    var severidad by remember { mutableStateOf("leve") }
    val severidades = listOf("leve", "moderado", "grave")

    var horaInicio by remember { mutableStateOf(LocalDateTime.now()) }
    var horaFin by remember { mutableStateOf<LocalDateTime?>(null) }

    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")

    fun pickDateTime(initial: LocalDateTime, onDateTimeSelected: (LocalDateTime) -> Unit) {
        val calendar = Calendar.getInstance().apply {
            set(initial.year, initial.monthValue - 1, initial.dayOfMonth, initial.hour, initial.minute)
        }
        DatePickerDialog(context, { _, year, month, dayOfMonth ->
            TimePickerDialog(context, { _, hourOfDay, minute ->
                val selected = LocalDateTime.of(year, month + 1, dayOfMonth, hourOfDay, minute)
                onDateTimeSelected(selected)
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
    }

    LaunchedEffect(state) {
        when (state) {
            is SintomasViewModel.RegistrarSintomaState.Success -> {
                snackbarHostState.showSnackbar("Síntoma registrado correctamente")
                navController.navigate("historial_sintomas") {
                    popUpTo("sintomas") { inclusive = true }
                }
            }
            is SintomasViewModel.RegistrarSintomaState.Error -> {
                val message = (state as SintomasViewModel.RegistrarSintomaState.Error).message
                snackbarHostState.showSnackbar(message)
            }
            else -> {}
        }
    }

    AppMonitoreoTheme {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.NoteAdd,
                        contentDescription = "Registrar síntoma",
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Registrar Síntoma",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    OutlinedTextField(
                        value = descripcion,
                        onValueChange = { descripcion = it },
                        label = { Text("Descripción") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                            focusedLabelColor = MaterialTheme.colorScheme.primary
                        ),
                        shape = MaterialTheme.shapes.medium,
                        singleLine = false,
                        maxLines = 4,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = androidx.compose.ui.text.input.ImeAction.Next)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Severidad",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.fillMaxWidth()
                    )

                    severidades.forEach { nivel ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                                .fillMaxWidth()
                        ) {
                            RadioButton(
                                selected = severidad == nivel,
                                onClick = { severidad = nivel },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = MaterialTheme.colorScheme.primary
                                )
                            )
                            Text(
                                text = nivel.replaceFirstChar { it.uppercase() },
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Hora de Inicio",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Button(
                        onClick = { pickDateTime(horaInicio) { selected -> horaInicio = selected } },
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium,
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text(
                            text = horaInicio.format(formatter),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Hora de Fin (opcional)",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Button(
                        onClick = { pickDateTime(horaFin ?: horaInicio) { selected -> horaFin = selected } },
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium,
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text(
                            text = horaFin?.format(formatter) ?: "Seleccionar hora de fin",
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = {
                            if (descripcion.isBlank()) {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Por favor, ingrese una descripción")
                                }
                                return@Button
                            }
                            if (horaFin != null && horaFin!!.isBefore(horaInicio)) {
                                scope.launch {
                                    snackbarHostState.showSnackbar("La hora de fin no puede ser anterior a la hora de inicio")
                                }
                                return@Button
                            }
                            val request = SintomaRequest(
                                hora_inicio = horaInicio.toString(),
                                hora_fin = horaFin?.toString(),
                                severidad = severidad,
                                descripcion = descripcion.trim()
                            )
                            viewModel.registrarSintoma(request)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = MaterialTheme.shapes.large,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                        )
                    ) {
                        if (state is SintomasViewModel.RegistrarSintomaState.Loading) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.size(24.dp)
                            )
                        } else {
                            Text(
                                text = "Guardar",
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }
            }
        }
    }
}
