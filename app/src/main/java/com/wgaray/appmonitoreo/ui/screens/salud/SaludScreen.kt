package com.wgaray.appmonitoreo.ui.screens.salud

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.WindowInsets
import com.wgaray.appmonitoreo.ui.theme.FondoApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaludScreen(
    viewModel: SaludViewModel = hiltViewModel()
) {
    val datos by viewModel.datos.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

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
                imageVector = Icons.Default.Favorite,
                contentDescription = "Mi salud",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Mi salud",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = "Visualiza tus signos vitales recientes",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.85f)
                )
            }
        }
        // Contenido principal
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
                    Text("Error: $errorMessage", color = MaterialTheme.colorScheme.error)
                }
            }
            datos != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                            .padding(horizontal = 16.dp, vertical = 18.dp),
                        verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {
                    datos?.let {
                            SignoCardModern(
                                titulo = "Frecuencia Cardíaca",
                                valor = "${it.frecuenciaCardiaca.valor} ${it.frecuenciaCardiaca.unidad}",
                                estado = it.frecuenciaCardiaca.estado
                            )
                            SignoCardModern(
                                titulo = "Presión Arterial",
                                valor = "${it.presionArterial.sistolica}/${it.presionArterial.diastolica} ${it.presionArterial.unidad}",
                                estado = it.presionArterial.estado
                            )
                            SignoCardModern(
                                titulo = "Pasos",
                                valor = "${it.pasos.cantidad} / ${it.pasos.metaDiaria}",
                                estado = it.pasos.estado
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SignoCardModern(titulo: String, valor: String, estado: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            // Tag de estado en la esquina superior derecha
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = when (estado.lowercase()) {
                    "normal" -> Color(0xFF00C853).copy(alpha = 0.15f)
                    "alerta" -> Color(0xFFFFA500).copy(alpha = 0.15f)
                    "riesgo" -> Color(0xFFE53935).copy(alpha = 0.15f)
                    else -> MaterialTheme.colorScheme.surfaceVariant
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 8.dp, end = 8.dp)
            ) {
                Text(
                    text = estado.replaceFirstChar { it.uppercase() },
                    color = when (estado.lowercase()) {
                        "normal" -> Color(0xFF00C853)
                        "alerta" -> Color(0xFFFFA500)
                        "riesgo" -> Color(0xFFE53935)
                        else -> MaterialTheme.colorScheme.onSurface
                    },
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp)
                )
            }
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = titulo,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            Text(
                    text = valor,
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
            )
            }
        }
    }
}
