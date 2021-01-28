package com.example.petgalleryapp.ui.home

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petgalleryapp.data.model.PetData
import com.example.petgalleryapp.data.model.UserData
import com.example.petgalleryapp.data.source.TaskResult
import com.example.petgalleryapp.data.source.repository.AnimalsRepository
import com.example.petgalleryapp.ui.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val animalsRepository: AnimalsRepository
) : BaseViewModel() {
    private var page = 1

    val listPetData = MutableLiveData<ArrayList<PetData>>()


    fun firstLoad() {
        page = 1
        viewModelScope.launch {
            when(val res = animalsRepository.getAnimals(page)){
                is TaskResult.Success -> {
                    listPetData.value = ArrayList(res.value)
                }

                is TaskResult.Failure -> {
                    Log.d("TAG", "fetchListData: ${res.value}" )
                }
            }
        }
    }



    fun loadMore() {
        if(_loadingState.value == LoadState.None){
            _loadingState.postValue(LoadState.Loading)
            viewModelScope.launch {
                when(val res = animalsRepository.getAnimals(++page)){
                    is TaskResult.Success -> {
                        listPetData.value?.let {
                            it.addAll(res.value)
                            listPetData.value = it
                        }
                        _loadingState.value = LoadState.None
                    }

                    is TaskResult.Failure -> {
                        _loadingState.value = LoadState.None
                        Log.d("TAG", "fetchListData: ${res.value}" )
                    }
                }
            }
        }

    }
}