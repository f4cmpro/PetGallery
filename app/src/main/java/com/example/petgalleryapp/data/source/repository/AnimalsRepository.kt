package com.example.petgalleryapp.data.source.repository

import com.example.petgalleryapp.Constant
import com.example.petgalleryapp.data.model.PetData
import com.example.petgalleryapp.data.source.ErrorResponse
import com.example.petgalleryapp.data.source.IRepository
import com.example.petgalleryapp.data.source.Response
import com.example.petgalleryapp.data.source.TaskResult
import com.example.petgalleryapp.data.source.remote.animals.GetAnimalsApiService
import javax.inject.Inject

interface IAnimalsRepository {
    suspend fun getAnimals(page: Int): TaskResult<List<PetData>, ErrorResponse>
}

class AnimalsRepository @Inject constructor (
    private val getAnimalsApiService: GetAnimalsApiService
)  : IRepository, IAnimalsRepository {

    override suspend fun getAnimals(page: Int): TaskResult<List<PetData>, ErrorResponse> {
        return TaskResult.Success(executeList(getAnimalsApiService.getAnimalsAsync(page, Constant.LIMIT_PAGE)))
    }
}