package com.tu.paquete.model

data class UserProfile(
    val name: String,
    val program: String,
    val semester: String,
    val imageUrl: String,
    val age: Int,
    val city: String,
    val email: String,
    val hobbies: List<String>,
    val sports: List<String>,
    val interests: List<String>
)