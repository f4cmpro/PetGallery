package com.example.petgalleryapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petgalleryapp.data.model.PetData
import com.example.petgalleryapp.data.model.UserData
import com.example.petgalleryapp.data.source.remote.animals.GetAnimalsApiService
import com.example.petgalleryapp.data.source.repository.AnimalsRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = AnimalsRepository()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    val listPetData = MutableLiveData<List<PetData>>()
    val text: LiveData<String> = _text


    fun fetchListData(){
        val user = UserData(1, "Jame Bond", "")
        val data = arrayListOf(
            PetData(1),
            PetData(2),
            PetData(3),
            PetData(4),
            PetData(5),
            PetData(6),
            PetData(7),
            PetData(8),
            PetData(9),
            PetData(10)
        )
        listPetData.value = data
    }


    private fun firstLoad() {
        viewModelScope.launch {
            val page = 1
            when(val response = timelineListUseCase.getTimelineList(page = page, isRefresh = true)) {
                is TaskResult.Success -> {
                    val dataList = response.value
                    canScroll = dataList.isNotEmpty() && dataList.size >= PAGE_SIZE
                    _pagingLoadState.postValue(if (dataList.isNotEmpty()) PagingLoadState.Loaded else PagingLoadState.Empty)
                }

                is TaskResult.Failure -> {
                    _pagingLoadState.postValue(PagingLoadState.Error)
                }

            }
        }
    }
}