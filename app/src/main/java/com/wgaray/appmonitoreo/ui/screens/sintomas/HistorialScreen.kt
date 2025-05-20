package com.wgaray.appmonitoreo.ui.screens.sintomas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wgaray.appmonitoreo.data.model.SintomaResponse
import com.wgaray.appmonitoreo.ui.screens.navigation.NavRoutes
import java.time.format.DateTimeFormatter

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

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Historial de Síntomas") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavRoutes.Sintomas) // Navega para agregar síntoma
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Síntoma")
            }
        }
    ) { paddingValues ->

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (errorMessage != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Error: $errorMessage", color = Color.Red)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(sintomas.size) { index ->
                    SintomaTimelineItem(sintoma = sintomas[index])
                    if (index < sintomas.size - 1) {
                        Divider(modifier = Modifier.padding(vertical = 8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun SintomaTimelineItem(sintoma: SintomaResponse) {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Timeline indicator
        Column(
            modifier = Modifier
                .width(40.dp)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            )
            Spacer(modifier = Modifier.height(4.dp))
            // Línea vertical que conecta puntos (puedes hacerlo con Canvas o Box)
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.primary)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = sintoma.descripcion,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Inicio: ${sintoma.hora_inicio.format(formatter)}",
                style = MaterialTheme.typography.bodyMedium
            )
            if (sintoma.hora_fin != null) {
                Text(
                    text = "Fin: ${sintoma.hora_fin?.format(formatter)}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Text(
                text = "Severidad: ${sintoma.severidad}",
                style = MaterialTheme.typography.bodyMedium,
                color = when (sintoma.severidad) {
                    "leve" -> Color.Green
                    "moderado" -> Color(0xFFFFA500) // naranja
                    "grave" -> Color.Red
                    else -> MaterialTheme.colorScheme.onSurface
                }
            )
        }
    }
}
