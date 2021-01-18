package com.example.petgalleryapp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Suppress("PropertyName")
data class LinkData(
    val self: String = "",
    val download: String = "",
    val downloadLocation: String = "",
    val html: String = "",
    val followers: String = "",
    val following: String = "",
    val likes: String = "",
    val photos: String = "",
    val portfolio: String = "",
)