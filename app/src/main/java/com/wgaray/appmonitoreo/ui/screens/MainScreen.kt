package com.wgaray.appmonitoreo.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wgaray.appmonitoreo.ui.screens.educacion.EducacionScreen
import com.wgaray.appmonitoreo.ui.screens.educacion.SeccionEduScreen
import com.wgaray.appmonitoreo.ui.screens.educacion.contenidoPorPaso
import com.wgaray.appmonitoreo.ui.screens.educacion.pasosEducativos
import com.wgaray.appmonitoreo.ui.screens.navigation.BottomNavigationBar
import com.wgaray.appmonitoreo.ui.screens.navigation.NavRoutes
import com.wgaray.appmonitoreo.ui.screens.salud.SaludScreen
import com.wgaray.appmonitoreo.ui.screens.sintomas.HistorialScreen
import com.wgaray.appmonitoreo.ui.screens.sintomas.RegistrarScreen
import com.wgaray.appmonitoreo.ui.screens.perfil.PerfilScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            tonalElevation = 0.5.dp
        ) {
            NavHost(
                navController = navController,
                startDestination = NavRoutes.Educacion
            ) {
                composable(NavRoutes.Educacion) {
                    EducacionScreen(navController)
                }
                composable(NavRoutes.Sintomas) {
                    RegistrarScreen(navController)
                }
                composable(NavRoutes.HistorialSintomas) {
                    HistorialScreen(navController)
                }
                composable(NavRoutes.DetallePasoConArg) { backStackEntry ->
                    val pasoId = backStackEntry.arguments?.getString("pasoId")?.toIntOrNull()
                    val paso = pasosEducativos.find { it.numero == pasoId }
                    val contenido = contenidoPorPaso[pasoId]

                    if (paso != null && contenido != null) {
                        SeccionEduScreen(paso, contenido)
                    } else {
                        Text("Contenido no disponible.")
                    }
                }
                composable(NavRoutes.Salud) {
                    SaludScreen()
                }
                // Agregar la ruta para registrar síntoma
                composable(NavRoutes.RegistrarSintoma) {
                    RegistrarScreen(navController)
                }
                // Ruta para la pantalla de perfil
                composable(NavRoutes.Perfil) {
                    PerfilScreen(navController)
                }
            }
        }
    }
}
