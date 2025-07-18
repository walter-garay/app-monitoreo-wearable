package com.wgaray.appmonitoreo.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.Image
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wgaray.appmonitoreo.ui.screens.navigation.NavRoutes
import com.wgaray.appmonitoreo.ui.theme.AppMonitoreoTheme
import androidx.compose.ui.res.painterResource
import com.wgaray.appmonitoreo.R
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    var email by remember { mutableStateOf("coly@gmail.com") }
    var password by remember { mutableStateOf("coly@gmail.com") }
    var passwordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(state) {
        when (state) {
            is LoginViewModel.LoginState.Success -> {
                snackbarHostState.showSnackbar("Inicio de sesión exitoso")
                navController.navigate(NavRoutes.Main) {
                    popUpTo(NavRoutes.Login) { inclusive = true }
                }
            }
            is LoginViewModel.LoginState.Error -> {
                snackbarHostState.showSnackbar((state as LoginViewModel.LoginState.Error).message)
            }
            else -> {}
        }
    }

    AppMonitoreoTheme {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .imePadding(), // para que el formulario se mueva con el teclado
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Icono de la app
                    Image(
                        painter = painterResource(id = R.drawable.mami_icono),
                        contentDescription = "Icono app",
                        modifier = Modifier
                            .size(90.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Iniciar sesión",
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Correo electrónico") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                            focusedLabelColor = MaterialTheme.colorScheme.primary
                        ),
                        shape = MaterialTheme.shapes.medium,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                            focusedLabelColor = MaterialTheme.colorScheme.primary
                        ),
                        shape = MaterialTheme.shapes.medium,
                        singleLine = true,
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        )
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = { viewModel.login(email, password) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        enabled = state !is LoginViewModel.LoginState.Loading,
                        shape = MaterialTheme.shapes.large,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                            disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                        )
                    ) {
                        if (state is LoginViewModel.LoginState.Loading) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.size(24.dp)
                            )
                        } else {
                            Text(
                                text = "Ingresar",
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    TextButton(
                        onClick = { navController.navigate("register") },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            buildAnnotatedString {
                                append("¿No tienes cuenta? ")
                                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("Regístrate aquí")
                                }
                            },
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}
