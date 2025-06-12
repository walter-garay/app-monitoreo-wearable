package com.wgaray.appmonitoreo.ui.screens.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val title: String
)

val bottomNavItems = listOf(
    BottomNavItem(NavRoutes.Educacion, Icons.Filled.Info, "Educación"),
    BottomNavItem(NavRoutes.Register, Icons.Filled.Person, "Register"),
    BottomNavItem(NavRoutes.Sintomas, Icons.Default.Warning, "Síntomas"),
    BottomNavItem(NavRoutes.Salud, Icons.Default.LocalHospital, "Salud")
)
