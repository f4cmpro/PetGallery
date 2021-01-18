package com.example.petgalleryapp.data.source.repository

import com.example.petgalleryapp.data.model.PetData
import com.example.petgalleryapp.data.source.ErrorResponse
import com.example.petgalleryapp.data.source.IRepository
import com.example.petgalleryapp.data.source.Response
import com.example.petgalleryapp.data.source.TaskResult
import com.example.petgalleryapp.data.source.remote.animals.GetAnimalsApiService

interface IAnimalsRepository {
    suspend fun getAnimals(page: Int, isRefresh: Boolean): TaskResult<List<PetData>, ErrorResponse>
}

class AnimalsRepository constructor (
    private val getAnimalsApiService: GetAnimalsApiService
)  : IRepository, IAnimalsRepository {

    override suspend fun getAnimals(page: Int, isRefresh: Boolean): TaskResult<List<PetData>, ErrorResponse> {
        return when(val res = execute(getAnimalsApiService.getAnimalsAsync(1, 10))) {
            is Response.Success -> {
                TaskResult.Success(res.value.animals)
            }
            is Response.Failure -> {
                TaskResult.Failure(res.value)
            }
        }
    }
}