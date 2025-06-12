package com.wgaray.appmonitoreo.ui.screens.educacion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wgaray.appmonitoreo.domain.model.PasoEducativo
import com.wgaray.appmonitoreo.ui.screens.navigation.NavRoutes
import com.wgaray.appmonitoreo.ui.theme.AppMonitoreoTheme

@Composable
fun EducacionScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Conoce la preeclampsia",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(pasosEducativos) { paso ->
                PasoCard(paso = paso) {
                    navController.navigate(NavRoutes.detallePasoRoute(paso.numero))
                }
            }
        }
    }
}

@Composable
fun PasoCard(paso: PasoEducativo, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${paso.numero}",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = paso.titulo,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = paso.descripcion,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EducacionScreenPreview() {
    AppMonitoreoTheme {
        // Este preview no puede usar NavController, solo se usa para el layout visual
        EducacionScreen(navController = rememberNavController())
    }
}
