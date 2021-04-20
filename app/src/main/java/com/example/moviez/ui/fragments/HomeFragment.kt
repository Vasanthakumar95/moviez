package com.example.moviez.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviez.R
import com.example.moviez.adapters.CategoryAdapter
import com.example.moviez.adapters.MovieListAdapter
import com.example.moviez.ui.viewModel.HomeFragmentViewModel
import com.example.moviez.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val homeFragmentViewModel : HomeFragmentViewModel by viewModels()
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        homeFragmentViewModel.categoryLiveData.observe(viewLifecycleOwner, Observer {
            when(it)
            {
                is Resource.Success->
                {
                    it.data?.let{
                        categoryAdapter.differ.submitList(it.genres)
                    }
                }
            }
        })

        homeFragmentViewModel.moviezListLiveData.observe(viewLifecycleOwner, Observer {
            when(it)
            {
                is Resource.Success->
                {
                    it.data?.let{
                        movieListAdapter.differ.submitList(it.results)
                    }
                }
            }
        })

        //category selection listener
        categoryAdapter.setOnItemClickListener {
            homeFragmentViewModel.getMovieListByCategory(it.id.toString())
        }


        movieListAdapter.setOnItemClickListener {
            var bundle = Bundle()
            bundle.putInt("id", it.id)
            findNavController().navigate(R.id.action_homeFragment_to_movieDetailsFragment, bundle)

        }

        // search input listener
        home_search_view.setOnQueryTextListener(object :  SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                homeFragmentViewModel.getMovieListBySearch(query)
                return false
            }

        })
    }



    private fun setupRecyclerView()
    {
        categoryAdapter = CategoryAdapter()
        rv_category.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        }

        movieListAdapter = MovieListAdapter()
        rv_movie_list.apply {
            adapter = movieListAdapter
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        }
    }
}