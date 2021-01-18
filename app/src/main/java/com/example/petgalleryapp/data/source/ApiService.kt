package com.example.petgalleryapp.data.source

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    /****************************************************************
     * Sample
     ****************************************************************/
    @GET("users/{user}/repos")
    suspend fun getProjectList(@Path("user") user: String): List<String>

}