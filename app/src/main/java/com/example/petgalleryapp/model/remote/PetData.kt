package com.example.petgalleryapp.model.remote

data class PetData(
    val id : Int = 0,
    val url : String = "",
    val user : UserData
)