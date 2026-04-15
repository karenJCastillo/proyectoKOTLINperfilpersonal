package com.example.perfil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.perfil.ui.navigation.AppNavigation
import com.example.perfil.ui.theme.PerfilTheme
import com.tu.paquete.viewmodel.ProfileViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            PerfilTheme {
                AppNavigation(viewModel)
            }
        }
    }
}