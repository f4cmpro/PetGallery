package com.example.petgalleryapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Suppress("PropertyName")
data class UserData(
    @Json(name = "id") val id: Int = 0,
    @Json(name = "name") val name: String = "",
    @Json(name = "avatar") val avatar: String = "",
    @Json(name = "accepted_tos") val acceptedTos: Boolean = false,
    @Json(name = "bio") val bio: String = "",
    @Json(name = "first_name") val firstName: String = "",
    @Json(name = "instagram_username") val instagramUsername: String = "",
    @Json(name = "last_name") val lastName: String = "",
    @Json(name = "links") val links: LinkData = LinkData(),
    @Json(name = "location") val location: String = "",
    @Json(name = "portfolio_url") val portfolioUrl: String = "",
    @Json(name = "profile_image") val profileImage: ProfileImageData = ProfileImageData(),
    @Json(name = "total_collections") val totalCollections: Int = 0,
    @Json(name = "total_likes") val totalLikes: Int = 0,
    @Json(name = "total_photos") val totalPhotos: Int = 0,
    @Json(name = "twitter_username") val twitterUsername: String = "",
    @Json(name = "updated_at") val updatedAt: String = "",
    @Json(name = "username") val username: String = "",
)