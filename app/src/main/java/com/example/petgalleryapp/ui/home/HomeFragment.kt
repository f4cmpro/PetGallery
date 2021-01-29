package com.example.petgalleryapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.petgalleryapp.R
import com.example.petgalleryapp.databinding.FragmentHomeBinding
import com.example.petgalleryapp.ui.home.adapter.PetGalleryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val petGalleryAdapter = PetGalleryAdapter(homeViewModel)
        binding.recyclerHome.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = petGalleryAdapter
            addOnScrollListener(scrollListener)
        }

        homeViewModel.apply {
            firstLoad()
            listPetData.observe(viewLifecycleOwner, {
                Log.d("TAG", "onViewCreated: ${it.size}")
                petGalleryAdapter.submitList(it)
            })
            loadingState.observe(viewLifecycleOwner, {

            })
        }
    }

    /**
     * Scroll listener
     */
    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val totalItemCount = layoutManager.itemCount
            val lastVisibleItem = layoutManager.findLastVisibleItemPosition() + VISIBLE_THRESHOLD

            val reachedVisibleThreshold = lastVisibleItem >= totalItemCount - 1

            Log.d("TAG", "onScrolled: $lastVisibleItem - $totalItemCount - $reachedVisibleThreshold")

            if (reachedVisibleThreshold) {
                homeViewModel.loadMore()
            }
        }
    }

    companion object {
        // The minimum number of items to have below your current scroll position before loading more.
        private const val VISIBLE_THRESHOLD = 3

        /**
         * Create new instance
         *
         * @return {@link HomeFragment}
         */
        fun newInstance() = HomeFragment()
    }
}