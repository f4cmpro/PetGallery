package com.example.petgalleryapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.petgalleryapp.R
import com.example.petgalleryapp.databinding.ItemPetGalleryBinding
import com.example.petgalleryapp.data.model.PetData
import com.example.petgalleryapp.ui.home.HomeViewModel

class PetGalleryAdapter(
    private val viewModel : HomeViewModel
) : ListAdapter<PetData, PetGalleryAdapter.PetViewHolder>(DIFF_CALLBACK){
    inner class PetViewHolder(private val parent : ViewGroup,
                              private val binding : ItemPetGalleryBinding =
                                  DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                                      R.layout.item_pet_gallery, parent, false))
        : RecyclerView.ViewHolder(binding.root){
            fun bind(data : PetData, homeViewModel: HomeViewModel) {
                binding.apply {
                    petData = data
                    viewModel = homeViewModel
                    binding.notifyChange()
                    binding.executePendingBindings()
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        return PetViewHolder(parent)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val itemData = getItem(position)
        holder.bind(itemData, viewModel)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
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