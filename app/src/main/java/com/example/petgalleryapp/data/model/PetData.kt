package com.example.petgalleryapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Suppress("PropertyName")
data class PetData(
    @Json(name = "id") val id: Int = 0,
    @Json(name = "alt_description") val altDescription: String = "",
    @Json(name = "blur_hash") val blurHash: String = "",
    @Json(name = "categories") val categories: List<String> = ArrayList(),
    @Json(name = "color") val color: String = "",
    @Json(name = "created_at") val createdAt: String = "",
    @Json(name = "current_user_collections") val currentUserCollections: List<UserData> = ArrayList(),
    @Json(name = "description") val description: String = "",
    @Json(name = "height") val height: Int = 0,
    @Json(name = "liked_by_user") val likedByUser: String = "",
    @Json(name = "likes") val likes: Int = 0,
    @Json(name = "links") val links: LinkData = LinkData(),
    @Json(name = "promoted_at") val promotedAt: String = "",
    @Json(name = "sponsorship") val sponsorship: String = "",
    @Json(name = "updated_at") val updatedAt: String = "",
    @Json(name = "urls") val urls: UrlData = UrlData(),
    @Json(name = "user") val user: UserData = UserData(),
    @Json(name = "width") val width: Int = 0
)