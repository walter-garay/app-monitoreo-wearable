package com.wgaray.appmonitoreo.ui.screens.sintomas

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.NoteAdd
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wgaray.appmonitoreo.data.model.SintomaRequest
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import com.wgaray.appmonitoreo.ui.theme.AppMonitoreoTheme
import androidx.compose.ui.text.font.FontWeight
import com.wgaray.appmonitoreo.ui.theme.FondoApp

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoApp)
    ) {
        // Encabezado compacto
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.primaryContainer
                        )
                    )
                )
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.NoteAdd,
                contentDescription = "Registrar síntoma",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Registrar síntoma",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = "Describe tu malestar y cuándo ocurrió",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.85f)
                )
            }
        }
        // Contenido principal
        Box(modifier = Modifier.weight(1f)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                    // Card principal
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            OutlinedTextField(
                                value = descripcion,
                                onValueChange = { descripcion = it },
                                label = { Text("Descripción del síntoma") },
                                placeholder = { Text("Ej: Dolor de cabeza, mareos...") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp)),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                                    unfocusedLabelColor = MaterialTheme.colorScheme.outline,
                                    cursorColor = MaterialTheme.colorScheme.primary
                                ),
                                shape = MaterialTheme.shapes.medium,
                                singleLine = false,
                                maxLines = 4,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Next
                                ),
                                textStyle = LocalTextStyle.current.copy(fontSize = 17.sp)
                            )

                            Spacer(modifier = Modifier.height(18.dp))

                            Text(
                                text = "Severidad",
                                style = MaterialTheme.typography.labelLarge,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                severidades.forEach { nivel ->
                                    FilterChip(
                                        selected = severidad == nivel,
                                        onClick = { severidad = nivel },
                                        label = {
                                            Text(nivel.replaceFirstChar { it.uppercase() })
                                        },
                                        leadingIcon = if (severidad == nivel) {
                                            {
                                                Icon(
                                                    imageVector = Icons.Default.Check,
                                                    contentDescription = null,
                                                    tint = MaterialTheme.colorScheme.primary
                                                )
                                            }
                                        } else null,
                                        colors = FilterChipDefaults.filterChipColors(
                                            selectedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                                            selectedLabelColor = MaterialTheme.colorScheme.primary,
                                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                            labelColor = MaterialTheme.colorScheme.onSurface
                                        )
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(18.dp))

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

                            Spacer(modifier = Modifier.height(12.dp))

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

                            Spacer(modifier = Modifier.height(24.dp))

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
                                    .height(52.dp),
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
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
