package com.example.perfil.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.perfil.ui.navigation.Routes
import com.tu.paquete.viewmodel.ProfileViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavHostController, viewModel: ProfileViewModel) {
    val user = viewModel.user
    val deepPink = Color(0xFFF06292)
    val backgroundPink = Color(0xFFFFF0F3)

    // Estados para controlar si están desplegados o no
    var hobbiesExpanded by remember { mutableStateOf(false) }
    var sportsExpanded by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = backgroundPink,
        topBar = {
            TopAppBar(
                title = { Text("Detalles", color = deepPink, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    TextButton(onClick = { navController.popBackStack() }) {
                        Text("←", color = deepPink, fontSize = 40.sp, fontWeight = FontWeight.Black)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundPink)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            // Sección Datos Personales (Siempre visible)
            item {
                SectionHeader("DATOS_PERSONALES", deepPink)
                InfoText("Edad", "${user.age} años")
                InfoText("Ciudad", user.city)
                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color(0xFFFFC1CC))
            }

            // Sección Hobbies Desplegable
            item {
                ExpandableHeader(
                    title = "HOBBIES",
                    isExpanded = hobbiesExpanded,
                    color = deepPink,
                    onToggle = { hobbiesExpanded = !hobbiesExpanded }
                )
            }
            item {
                AnimatedVisibility(visible = hobbiesExpanded) {
                    Column(modifier = Modifier.padding(start = 16.dp, bottom = 12.dp)) {
                        user.hobbies.forEach { hobby ->
                            Text("• $hobby", modifier = Modifier.padding(vertical = 2.dp))
                        }
                    }
                }
            }

            // Sección Deportes Desplegable
            item {
                ExpandableHeader(
                    title = "DEPORTES",
                    isExpanded = sportsExpanded,
                    color = deepPink,
                    onToggle = { sportsExpanded = !sportsExpanded }
                )
            }
            item {
                AnimatedVisibility(visible = sportsExpanded) {
                    Column(modifier = Modifier.padding(start = 16.dp, bottom = 12.dp)) {
                        user.sports.forEach { deporte ->
                            Text("• $deporte", modifier = Modifier.padding(vertical = 2.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExpandableHeader(title: String, isExpanded: Boolean, color: Color, onToggle: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle() }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
            color = color
        )
        Text(
            text = if (isExpanded) "▲" else "▼",
            color = color,
            fontSize = 14.sp
        )
    }
}

@Composable
fun SectionHeader(title: String, color: Color) {
    Text(
        text = title,
        fontSize = 16.sp,
        fontWeight = FontWeight.ExtraBold,
        color = color,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
fun InfoText(label: String, value: String) {
    Row(modifier = Modifier.padding(vertical = 2.dp)) {
        Text(text = "$label: ", fontWeight = FontWeight.Bold, color = Color.Gray)
        Text(text = value)
    }
}