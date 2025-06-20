package com.wgaray.appmonitoreo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.wgaray.appmonitoreo.ui.screens.auth.RegisterScreen
import com.wgaray.appmonitoreo.ui.theme.AppMonitoreoTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wgaray.appmonitoreo.ui.screens.navigation.NavRoutes
import com.wgaray.appmonitoreo.ui.screens.MainScreen
import com.wgaray.appmonitoreo.ui.screens.auth.LoginScreen
import com.wgaray.appmonitoreo.ui.screens.educacion.EducacionScreen
import com.wgaray.appmonitoreo.ui.screens.educacion.SeccionEduScreen
import com.wgaray.appmonitoreo.ui.screens.educacion.contenidoPorPaso
import com.wgaray.appmonitoreo.ui.screens.educacion.pasosEducativos
import com.wgaray.appmonitoreo.ui.screens.sintomas.HistorialScreen
import com.wgaray.appmonitoreo.ui.screens.sintomas.RegistrarScreen


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppMonitoreoTheme {
                // val navController = rememberNavController() // crea el controlador
                // RegisterScreen(navController = navController) // pásalo como parámetro

                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Register
    ) {
        composable(NavRoutes.Register) {
            RegisterScreen(navController)
        }
        composable(NavRoutes.Login) {
            LoginScreen(navController)
        }
        composable(NavRoutes.Main) {
            MainScreen() // Aquí inicia todo con barra de navegación inferior
        }
    }
}

