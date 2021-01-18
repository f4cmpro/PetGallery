package com.example.petgalleryapp.data.source.remote.animals

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface GetAnimalsApiService {

    @GET("napi/topics/animals/photos")
    suspend fun getAnimalsAsync(@Query("page") page: Int, @Query("per_page") perPage : Int): Deferred<GetAnimalsResponse>

}