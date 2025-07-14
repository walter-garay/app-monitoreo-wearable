package com.wgaray.appmonitoreo.ui.screens.sintomas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Brush
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wgaray.appmonitoreo.data.model.SintomaResponse
import java.time.format.DateTimeFormatter
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Offset
import com.wgaray.appmonitoreo.ui.theme.FondoApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialScreen(
    navController: NavController,
    viewModel: HistorialViewModel = hiltViewModel()
) {
    val sintomas by viewModel.sintomas.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarSintomas()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoApp)
    ) {
        // Encabezado compacto y colorido
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
                imageVector = Icons.Default.MedicalServices,
                contentDescription = "Síntomas",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Síntomas",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = "Revisa tus síntomas registrados",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.85f)
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        // Contenido principal (timeline + lista)
        Box(modifier = Modifier.weight(1f)) {
            when {
                isLoading -> {
            Box(
                        modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
                }
                errorMessage != null -> {
            Box(
                        modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Error: $errorMessage", color = Color.Red)
            }
                }
                else -> {
                    HistorialTimelineList(sintomas)
                }
            }
        }
    }
}

@Composable
fun HistorialTimelineList(sintomas: List<SintomaResponse>) {
    val timelineColor = Color(0xFFBDBDBD) // Gris
    Box(modifier = Modifier.fillMaxSize()) {
        // Línea vertical continua
        Canvas(modifier = Modifier
            .fillMaxHeight()
            .width(40.dp)
            .align(Alignment.TopStart)
        ) {
            drawLine(
                color = timelineColor,
                start = Offset(x = size.width / 2f, y = 0f),
                end = Offset(x = size.width / 2f, y = size.height),
                strokeWidth = 4f
            )
        }
        // Lista de síntomas
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                .padding(start = 0.dp, end = 0.dp, top = 0.dp, bottom = 0.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(sintomas.size) { index ->
                SintomaCardTimeline(
                    sintoma = sintomas[index],
                    isFirst = index == 0,
                    isLast = index == sintomas.size - 1
                )
            }
        }
    }
}

@Composable
fun SintomaCardTimeline(sintoma: SintomaResponse, isFirst: Boolean, isLast: Boolean) {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Timeline indicator visual
        Box(
            modifier = Modifier
                .width(32.dp) // Reduce el ancho de la columna lateral
                .fillMaxHeight(),
            contentAlignment = Alignment.TopCenter
        ) {
            // Círculo gris claro perfectamente centrado en la línea
            Box(
                modifier = Modifier
                    .size(9.dp)
                    .offset(x = 3.8.dp) // (32dp - 9dp) / 2 = 11.5dp
                    .clip(CircleShape)
                    .background(Color(0xFFBDBDBD))
            )
        }
        Spacer(modifier = Modifier.width(6.dp))
        // Contenedor columna: fecha arriba, card abajo
        Column(
                modifier = Modifier
                .fillMaxWidth()
                .padding(end = 12.dp) // Espacio derecho entre la lista y el borde de pantalla
        ) {
            // Fecha alineada verticalmente al círculo
            Text(
                text = sintoma.hora_inicio.format(formatter),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(start = 4.dp, bottom = 2.dp)
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    // Tag de severidad en la esquina superior derecha
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = when (sintoma.severidad) {
                            "leve" -> Color(0xFF00C853).copy(alpha = 0.15f) // Verde vivo
                            "moderado" -> Color(0xFFFFA500).copy(alpha = 0.15f)
                            "grave" -> Color(0xFFE53935).copy(alpha = 0.15f)
                            else -> MaterialTheme.colorScheme.surfaceVariant
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 8.dp, end = 8.dp)
                    ) {
                        Text(
                            text = sintoma.severidad.replaceFirstChar { it.uppercase() },
                            color = when (sintoma.severidad) {
                                "leve" -> Color(0xFF00C853) // Verde vivo
                                "moderado" -> Color(0xFFFFA500)
                                "grave" -> Color(0xFFE53935)
                                else -> MaterialTheme.colorScheme.onSurface
                            },
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp)
                        )
                    }
                    Column(modifier = Modifier.padding(16.dp)) {
            Text(
                            text = sintoma.descripcion,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFF757575) // Gris
            )
            if (sintoma.hora_fin != null) {
                Text(
                    text = "Fin: ${sintoma.hora_fin?.format(formatter)}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
                    }
                }
            }
        }
    }
}
