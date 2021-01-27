package com.example.petgalleryapp.data.source

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetDataResponse <T>(
    @Json(name = "data") val data: T
) : ResponseBase()