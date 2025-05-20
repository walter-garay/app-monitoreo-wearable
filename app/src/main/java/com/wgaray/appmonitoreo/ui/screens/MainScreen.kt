package com.wgaray.appmonitoreo.ui.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wgaray.appmonitoreo.ui.screens.educacion.EducacionScreen
import com.wgaray.appmonitoreo.ui.screens.navigation.BottomNavigationBar
import com.wgaray.appmonitoreo.ui.screens.navigation.NavRoutes
import com.wgaray.appmonitoreo.ui.screens.sintomas.HistorialScreen
import com.wgaray.appmonitoreo.ui.screens.sintomas.RegistrarScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = NavRoutes.Educacion,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(NavRoutes.Educacion) {
                EducacionScreen()
            }
            composable(NavRoutes.Sintomas) {
                RegistrarScreen(navController)
            }
            composable(NavRoutes.HistorialSintomas) {
                HistorialScreen(navController)
            }

            // Agrega aquí otras pantallas si las tienes
        }
    }
}
