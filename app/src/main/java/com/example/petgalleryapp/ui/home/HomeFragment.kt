package com.example.petgalleryapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petgalleryapp.R
import com.example.petgalleryapp.databinding.FragmentHomeBinding
import com.example.petgalleryapp.ui.home.adapter.PetGalleryAdapter

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val petGalleryAdapter = PetGalleryAdapter(homeViewModel)
        binding.recyclerHome.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = petGalleryAdapter
        }

        homeViewModel.apply {
            fetchListData()

            listPetData.observe(viewLifecycleOwner, {
                petGalleryAdapter.submitList(it)
            })
        }
    }
}