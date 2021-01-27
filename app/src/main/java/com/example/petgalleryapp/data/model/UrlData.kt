package com.example.petgalleryapp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Suppress("PropertyName")
data class UrlData(
    val full: String? = "",
    val raw: String? = "",
    val regular: String? = "",
    val small: String? = "",
    val thumb: String? = ""
)
