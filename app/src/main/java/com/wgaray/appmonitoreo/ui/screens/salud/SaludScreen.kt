package com.wgaray.appmonitoreo.ui.screens.salud

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaludScreen(
    viewModel: SaludViewModel = hiltViewModel()
) {
    val datos by viewModel.datos.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi salud") }
            )
        }
    ) { paddingValues ->
        when {
            isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            errorMessage != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Error: $errorMessage", color = MaterialTheme.colorScheme.error)
                }
            }
            datos != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    datos?.let {
                        SignoCard("Frecuencia Cardíaca", "${it.frecuenciaCardiaca.valor} ${it.frecuenciaCardiaca.unidad}", it.frecuenciaCardiaca.estado)
                        SignoCard("Presión Arterial", "${it.presionArterial.sistolica}/${it.presionArterial.diastolica} ${it.presionArterial.unidad}", it.presionArterial.estado)
                        SignoCard("Pasos", "${it.pasos.cantidad} / ${it.pasos.metaDiaria}", it.pasos.estado)
                    }
                }
            }
        }
    }
}

@Composable
fun SignoCard(titulo: String, valor: String, estado: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = titulo, style = MaterialTheme.typography.titleMedium)
            Text(text = valor, style = MaterialTheme.typography.headlineSmall)
            Text(
                text = "Estado: $estado",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}
