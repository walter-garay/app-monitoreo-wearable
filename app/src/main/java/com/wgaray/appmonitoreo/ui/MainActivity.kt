package com.wgaray.appmonitoreo.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.collectAsState


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // Obtener el ViewModel usando Hilt
    private val mainViewModel: MainViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Solicitar el permiso al iniciar la actividad
        mainViewModel.checkAndRequestPermission(this)

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
    val sessionViewModel: com.wgaray.appmonitoreo.ui.screens.auth.SessionViewModel = androidx.hilt.navigation.compose.hiltViewModel()
    val usuario = sessionViewModel.session.collectAsState().value
    val startDestination = if (usuario != null) com.wgaray.appmonitoreo.ui.screens.navigation.NavRoutes.Main else com.wgaray.appmonitoreo.ui.screens.navigation.NavRoutes.Login

    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(com.wgaray.appmonitoreo.ui.screens.navigation.NavRoutes.Register) {
            com.wgaray.appmonitoreo.ui.screens.auth.RegisterScreen(navController)
        }
        composable(com.wgaray.appmonitoreo.ui.screens.navigation.NavRoutes.Login) {
            com.wgaray.appmonitoreo.ui.screens.auth.LoginScreen(navController)
        }
        composable(com.wgaray.appmonitoreo.ui.screens.navigation.NavRoutes.Main) {
            com.wgaray.appmonitoreo.ui.screens.MainScreen()
        }
    }
}

