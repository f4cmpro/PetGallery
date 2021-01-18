package com.example.petgalleryapp.data.source.remote.animals

import com.example.petgalleryapp.data.model.PetData
import com.example.petgalleryapp.data.source.ResponseBase
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class GetAnimalsResponse(
    @Json(name = "data") val animals: List<PetData>
) : ResponseBase()