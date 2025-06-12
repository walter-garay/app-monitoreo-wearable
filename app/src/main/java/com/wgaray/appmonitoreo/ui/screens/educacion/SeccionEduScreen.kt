package com.wgaray.appmonitoreo.ui.screens.educacion

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.wgaray.appmonitoreo.domain.model.BloqueContenido
import com.wgaray.appmonitoreo.domain.model.PasoEducativo

@Composable
fun SeccionEduScreen(paso: PasoEducativo, contenido: List<BloqueContenido>) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text(
            text = paso.titulo,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        contenido.forEach { bloque ->
            when (bloque) {
                is BloqueContenido.Parrafo -> Text(
                    text = bloque.texto,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                is BloqueContenido.Subtitulo -> Text(
                    text = bloque.texto,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                is BloqueContenido.Imagen -> Image(
                    painter = painterResource(id = bloque.resId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .padding(vertical = 8.dp)
                )
                is BloqueContenido.Lista -> Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    bloque.items.forEach { item ->
                        Row {
                            Text("• ", style = MaterialTheme.typography.bodyLarge)
                            Text(item, style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp)) // separación final para que no quede "pegado" al bottom nav
    }
}
