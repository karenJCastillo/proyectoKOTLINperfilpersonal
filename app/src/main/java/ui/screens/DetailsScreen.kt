package com.example.perfil.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tu.paquete.viewmodel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavHostController, viewModel: ProfileViewModel) {
    val user = viewModel.user
    val deepPink = Color(0xFFF06292)
    val backgroundPink = Color(0xFFFFF0F3)

    Scaffold(
        containerColor = backgroundPink,
        topBar = {
            TopAppBar(
                title = { Text("Detalles", color = deepPink, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    // Flecha más grande sin texto
                    TextButton(onClick = { navController.popBackStack() }) {
                        Text(
                            text = "←",
                            color = deepPink,
                            fontSize = 40.sp, // Tamaño gigante
                            fontWeight = FontWeight.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundPink)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 16.dp)
        ) {
            item {
                SectionHeader("DATOS_PERSONALES_", deepPink) // Guion bajo
                InfoText("Edad", "${user.age} años")
                InfoText("Ciudad", user.city)
                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color(0xFFFFC1CC))
            }

            item { SectionHeader("HOBBIES_", deepPink) }
            items(user.hobbies) { Text(it, modifier = Modifier.padding(vertical = 2.dp)) }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionHeader("DEPORTES_", deepPink)
            }
            items(user.sports) { Text(it, modifier = Modifier.padding(vertical = 2.dp)) }
        }
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