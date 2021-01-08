package com.example.petgalleryapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.petgalleryapp.R
import com.example.petgalleryapp.databinding.ItemPetGalleryBinding
import com.example.petgalleryapp.model.remote.PetData
import com.example.petgalleryapp.ui.home.HomeViewModel

class PetGalleryAdapter(
    private val viewModel : HomeViewModel
) : ListAdapter<PetData, PetGalleryAdapter.PetViewHolder>(DIFF_CALLBACK){
    inner class PetViewHolder(private val parent : ViewGroup,
                              private val binding : ItemPetGalleryBinding =
                                  DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_pet_gallery, parent, false))
        : RecyclerView.ViewHolder(binding.root){
            fun bind(data : PetData, viewModel: HomeViewModel) {

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


    companion object {

        val DIFF_CALLBACK: DiffUtil.ItemCallback<PetData> = object : DiffUtil.ItemCallback<PetData>() {
            override fun areItemsTheSame(oldItem: PetData, newItem: PetData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PetData, newItem: PetData): Boolean {
                return oldItem == newItem
            }
        }

    }
}