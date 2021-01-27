package com.example.petgalleryapp.data.source.remote.animals

import com.example.petgalleryapp.data.model.PetData
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface GetAnimalsApiService {

    @GET("napi/topics/animals/photos")
    fun getAnimalsAsync(@Query("page") page: Int, @Query("per_page") perPage : Int): Deferred<List<PetData>>

}