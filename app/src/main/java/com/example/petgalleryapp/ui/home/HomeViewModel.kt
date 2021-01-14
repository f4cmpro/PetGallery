package com.example.petgalleryapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.petgalleryapp.data.model.PetData
import com.example.petgalleryapp.data.model.UserData

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    val listPetData = MutableLiveData<List<PetData>>()
    val text: LiveData<String> = _text


    fun fetchListData(){
        val user = UserData(1, "Jame Bond", "")
        val data = arrayListOf(
            PetData(1, "", user),
            PetData(2, "", user),
            PetData(3, "", user),
            PetData(4, "", user),
            PetData(5, "", user),
            PetData(6, "", user),
            PetData(7, "", user),
            PetData(8, "", user),
            PetData(9, "", user),
            PetData(10, "", user),
        )
        listPetData.value = data
    }
}