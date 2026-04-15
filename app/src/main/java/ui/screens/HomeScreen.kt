package com.example.perfil.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.perfil.ui.navigation.Routes
import com.tu.paquete.viewmodel.ProfileViewModel

import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController, viewModel: ProfileViewModel) {
    val user = viewModel.user
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var toquesRealizados by remember { mutableIntStateOf(0) }
    val juegoTerminado = toquesRealizados >= 3

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val infiniteTransitionX = rememberInfiniteTransition(label = "movimientoX")
    val animX by infiniteTransitionX.animateValue(
        initialValue = 0.dp,
        targetValue = screenWidth - 140.dp,
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "x"
    )

    val infiniteTransitionY = rememberInfiniteTransition(label = "movimientoY")
    val animY by infiniteTransitionY.animateValue(
        initialValue = 0.dp,
        targetValue = screenHeight - 160.dp,
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(2500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "y"
    )

    val deepPink = Color(0xFFF06292)
    val backgroundPink = Color(0xFFFFF0F3)
    val lovelyPink = Color(0xFFFF80AB)
    val defaultUserImage = "https://cdn-icons-png.flaticon.com/512/3135/3135715.png"

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = false,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.fillMaxWidth(0.6f).fillMaxHeight(),
                drawerContainerColor = backgroundPink
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(40.dp))

                    AsyncImage(
                        model = user.imageUrl,
                        contentDescription = "Foto",
                        modifier = Modifier.size(100.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop,
                        error = rememberAsyncImagePainter(defaultUserImage)
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "${user.name}_", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = deepPink)
                    Text(text = "${user.program}_", fontSize = 14.sp, color = Color.Gray)

                    Spacer(modifier = Modifier.height(30.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFC1CC))
                    ) {
                        TextButton(
                            onClick = {
                                scope.launch { drawerState.close() }
                                navController.navigate(Routes.Details.route)
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("DETALLES", fontWeight = FontWeight.Bold, color = deepPink)
                        }
                    }
                }
            }
        }
    ) {
        Scaffold(containerColor = backgroundPink) { padding ->
            Box(modifier = Modifier.fillMaxSize().padding(padding)) {
                Text(
                    text = if (juegoTerminado) "" else "ATRÁPAME: $toquesRealizados / 3",
                    modifier = Modifier.align(Alignment.TopCenter).padding(top = 60.dp),
                    color = deepPink,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Button(
                    onClick = {
                        if (juegoTerminado) {
                            scope.launch { drawerState.open() }
                        } else {
                            toquesRealizados++
                        }
                    },
                    modifier = if (juegoTerminado) {
                        Modifier.align(Alignment.Center).size(width = 180.dp, height = 60.dp)
                    } else {
                        Modifier.offset(x = animX, y = animY).size(width = 130.dp, height = 50.dp)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (juegoTerminado) lovelyPink else deepPink
                    )
                ) {
                    Text(
                        text = if (juegoTerminado) "ABRIR PERFIL" else "¡ATRAPAME!",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}