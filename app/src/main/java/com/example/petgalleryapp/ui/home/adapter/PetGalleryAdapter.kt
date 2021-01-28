package com.example.petgalleryapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.petgalleryapp.R
import com.example.petgalleryapp.data.model.PetData
import com.example.petgalleryapp.databinding.ItemLoadMoreBinding
import com.example.petgalleryapp.databinding.ItemPetGalleryBinding
import com.example.petgalleryapp.ui.home.HomeViewModel

class PetGalleryAdapter(
    private val viewModel: HomeViewModel
) : ListAdapter<PetData, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    inner class PetViewHolder(
        private val parent: ViewGroup,
        private val binding: ItemPetGalleryBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_pet_gallery, parent, false
            )
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PetData, homeViewModel: HomeViewModel) {
            binding.apply {
                petData = data
                viewModel = homeViewModel
                binding.notifyChange()
                binding.executePendingBindings()
            }
        }
    }

    inner class LoadMoreViewHolder(
        private val parent: ViewGroup,
        private val binding: ItemLoadMoreBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_load_more, parent, false
            )
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: HomeViewModel) {
            binding.state = viewModel.loadingState.value
            binding.notifyChange()
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == PET_GALLERY_ITEM_TYPE){
            PetViewHolder(parent)
        }else{
            LoadMoreViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemData = getItem(position)
        if(getItemViewType(position) == PET_GALLERY_ITEM_TYPE){
            (holder as  PetViewHolder).bind(itemData, viewModel)
        }else{
            (holder as  LoadMoreViewHolder).bind(viewModel)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) {
            LOAD_MORE_ITEM_TYPE
        } else {
            PET_GALLERY_ITEM_TYPE
        }
    }

    companion object {

        val DIFF_CALLBACK: DiffUtil.ItemCallback<PetData> =
            object : DiffUtil.ItemCallback<PetData>() {
                override fun areItemsTheSame(oldItem: PetData, newItem: PetData): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: PetData, newItem: PetData): Boolean {
                    return oldItem == newItem
                }
            }

        const val PET_GALLERY_ITEM_TYPE = 1
        const val LOAD_MORE_ITEM_TYPE = 2
    }
}