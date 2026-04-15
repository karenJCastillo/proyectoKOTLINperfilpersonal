package com.tu.paquete.viewmodel

import androidx.lifecycle.ViewModel
import com.tu.paquete.model.UserProfile

class ProfileViewModel : ViewModel() {
    val user = UserProfile(
        name = "Karen Juliana Castillo",
        program = "Ingeniería de Software",
        semester = "5to Semestre",
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQUgHzsjmsAqdHuR_Gmp118gHZOP6m-CRHlPQ&s",
        age = 19,
        city = "Chía, Colombia",
        email = "kcastillo@ucundinamarca.edu.co",
        hobbies = listOf("Ver Anime", "Bailar", "Tejer"),
        sports = listOf("Voleyball"),
        interests = listOf("Animales", "Amigurrumis")
    )
}