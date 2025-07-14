package com.wgaray.appmonitoreo.ui.screens.educacion

import android.annotation.SuppressLint
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.wgaray.appmonitoreo.domain.model.BloqueContenido
import com.wgaray.appmonitoreo.domain.model.PasoEducativo

@Composable
fun SeccionEduScreen(paso: PasoEducativo, contenido: List<BloqueContenido>) {
    val scrollState = rememberScrollState()
    val horizontalPadding = 18.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(com.wgaray.appmonitoreo.ui.theme.FondoApp)
            .padding(horizontal = horizontalPadding)
            .verticalScroll(scrollState)
    ) {
        // Cabecera visual
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 12.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(24.dp),
                tonalElevation = 6.dp,
                shadowElevation = 8.dp,
                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.95f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(vertical = 24.dp, horizontal = 8.dp)
                ) {
                    Text(
                        text = paso.titulo,
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, fontSize = 26.sp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        contenido.forEachIndexed { idx, bloque ->
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                when (bloque) {
                    is BloqueContenido.Parrafo -> Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .shadow(6.dp, RoundedCornerShape(18.dp)),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.95f)
                        ),
                        shape = RoundedCornerShape(18.dp)
                    ) {
                        Text(
                            text = bloque.texto,
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                            modifier = Modifier.padding(18.dp),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    is BloqueContenido.Subtitulo -> Text(
                        text = bloque.texto,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, fontSize = 22.sp),
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(vertical = 14.dp)
                    )
                    is BloqueContenido.Imagen -> Image(
                        painter = painterResource(id = bloque.resId),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(210.dp)
                            .clip(RoundedCornerShape(18.dp))
                            .padding(vertical = 10.dp)
                    )
                    is BloqueContenido.Lista -> Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                    ) {
                        bloque.items.forEach { item ->
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 6.dp)) {
                                Surface(
                                    shape = CircleShape,
                                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.18f),
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Info,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.padding(4.dp).size(20.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(item, style = MaterialTheme.typography.bodyLarge.copy(fontSize = 17.sp), color = MaterialTheme.colorScheme.onSurface)
                            }
                        }
                    }
                    is BloqueContenido.Cita -> Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                            .shadow(4.dp, RoundedCornerShape(16.dp)),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.95f)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "\"${bloque.texto}\"",
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Medium, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic, fontSize = 18.sp),
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(18.dp)
                        )
                    }
                    is BloqueContenido.Alerta -> Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                            .shadow(4.dp, RoundedCornerShape(16.dp)),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFF3CD)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(18.dp)) {
                            Surface(
                                shape = CircleShape,
                                color = Color(0xFFFFC107).copy(alpha = 0.18f),
                                modifier = Modifier.size(28.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Warning,
                                    contentDescription = "Alerta",
                                    tint = Color(0xFFFFC107),
                                    modifier = Modifier.padding(4.dp).size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = bloque.texto,
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, fontSize = 17.sp),
                                color = Color(0xFF856404)
                            )
                        }
                    }
                    is BloqueContenido.Video -> VideoYoutubeBlock(bloque.url)
                    is BloqueContenido.Separador -> Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.18f),
                        thickness = 2.dp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp)) // separación final para que no quede "pegado" al bottom nav
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun VideoYoutubeBlock(youtubeUrl: String) {
    val context = LocalContext.current
    val density = LocalDensity.current
    val videoId = extractYoutubeId(youtubeUrl)
    val embedUrl = "https://www.youtube.com/embed/$videoId"
    val videoHeightPx = with(density) { 220.dp.roundToPx() }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .shadow(6.dp, RoundedCornerShape(18.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.98f)
        ),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AndroidView(
                factory = { ctx ->
                    WebView(ctx).apply {
                        layoutParams = android.view.ViewGroup.LayoutParams(
                            android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                            videoHeightPx
                        )
                        webViewClient = WebViewClient()
                        settings.javaScriptEnabled = true
                        settings.domStorageEnabled = true
                        settings.loadWithOverviewMode = true
                        settings.useWideViewPort = true
                        settings.cacheMode = WebSettings.LOAD_DEFAULT
                        loadUrl(embedUrl)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Video educativo",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

fun extractYoutubeId(url: String): String {
    val regex = Regex("(?:v=|be/|embed/)([a-zA-Z0-9_-]{11})")
    return regex.find(url)?.groupValues?.get(1) ?: ""
}
