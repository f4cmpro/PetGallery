package com.example.petgalleryapp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Suppress("PropertyName")
data class ProfileImageData(
    val large : String = "",
    val medium : String = "",
    val small : String = ""
)
