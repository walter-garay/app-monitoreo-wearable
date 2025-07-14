package com.wgaray.appmonitoreo.ui.screens.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.School
import androidx.compose.material.icons.rounded.MedicalServices
import androidx.compose.ui.graphics.vector.ImageVector

// Iconos: Salud = Favorite, Educación = School

data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val title: String,
    val isCentral: Boolean = false
)

val bottomNavItems = listOf(
    BottomNavItem(NavRoutes.Salud, Icons.Rounded.Favorite, "Salud"),
    BottomNavItem(NavRoutes.Educacion, Icons.Rounded.School, "Educación"),
    BottomNavItem("registrar_sintoma", Icons.Rounded.AddCircle, "Agregar", isCentral = true),
    BottomNavItem("historial_sintomas", Icons.Rounded.MedicalServices, "Síntomas"),
    BottomNavItem(NavRoutes.Register, Icons.Rounded.Person, "Perfil")
)
