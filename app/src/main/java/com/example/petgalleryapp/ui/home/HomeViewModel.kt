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
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val animalsRepository: AnimalsRepository
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    val listPetData = MutableLiveData<List<PetData>>()
    val text: LiveData<String> = _text


    fun fetchListData(){
        viewModelScope.launch {
            when(val res = animalsRepository.getAnimals(1, false)){
                is TaskResult.Success -> {
                    listPetData.value = res.value
                }

                is TaskResult.Failure -> {
                    Log.d("TAG", "fetchListData: ${res.value}" )
                }
            }
        }
    }


    private fun firstLoad() {
        viewModelScope.launch {
            val page = 1
//            when(val response = timelineListUseCase.getTimelineList(page = page, isRefresh = true)) {
//                is TaskResult.Success -> {
//                    val dataList = response.value
//                    canScroll = dataList.isNotEmpty() && dataList.size >= PAGE_SIZE
//                    _pagingLoadState.postValue(if (dataList.isNotEmpty()) PagingLoadState.Loaded else PagingLoadState.Empty)
//                }
//
//                is TaskResult.Failure -> {
//                    _pagingLoadState.postValue(PagingLoadState.Error)
//                }
//
//            }
        }
    }
}