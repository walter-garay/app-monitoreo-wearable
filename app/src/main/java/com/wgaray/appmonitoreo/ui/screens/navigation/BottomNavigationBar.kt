package com.wgaray.appmonitoreo.ui.screens.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.remember

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = bottomNavItems
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Línea divisoria superior
    Divider(
        color = Color(0x22000000), // gris suave
        thickness = 1.5.dp,
        modifier = Modifier.fillMaxWidth()
    )

    Surface(
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
        color = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .shadow(
                elevation = 8.dp,
                spotColor = Color(0x22000000), // sombra superior suave
                ambientColor = Color(0x22000000),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                clip = false
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
        items.forEach { item ->
                val selected = currentRoute == item.route
                if (item.isCentral) {
                    CentralNavBarItem(
                        selected = selected,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = item.icon,
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    NavBarItem(
                        item = item,
                        selected = selected,
                        onClick = {
                            if (currentRoute != item.route) {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun NavBarItem(item: BottomNavItem, selected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val selectedColor = MaterialTheme.colorScheme.primary
    val unselectedColor = Color(0xFFB0B0B0)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(vertical = 6.dp)
            .background(Color.Transparent)
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
    ) {
        if (selected) {
            Surface(
                color = selectedColor.copy(alpha = 0.15f),
                shape = CircleShape,
                modifier = Modifier.size(38.dp)
            ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Icon(item.icon, contentDescription = item.title, tint = selectedColor, modifier = Modifier.size(24.dp))
                }
            }
        } else {
            Icon(item.icon, contentDescription = item.title, tint = unselectedColor, modifier = Modifier.size(28.dp))
        }
        Text(item.title, color = if (selected) selectedColor else unselectedColor, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
private fun CentralNavBarItem(selected: Boolean, onClick: () -> Unit, icon: ImageVector, modifier: Modifier = Modifier) {
    val color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .offset(y = (-12).dp)
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
    ) {
        Surface(
            color = color,
            shape = CircleShape,
            shadowElevation = 8.dp,
            modifier = Modifier.size(56.dp)
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Icon(icon, contentDescription = "Agregar síntoma", tint = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(34.dp))
            }
        }
        // Sin subtítulo debajo
    }
}