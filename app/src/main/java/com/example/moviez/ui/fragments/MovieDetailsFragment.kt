package com.example.moviez.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviez.R
import com.example.moviez.adapters.DetailsCategoryAdapter
import com.example.moviez.adapters.DetailsLanguageAdapter
import com.example.moviez.ui.viewModel.MovieDetailsFragmentViewModel
import com.example.moviez.util.Constants.Companion.IMG_URL
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_details.*

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private val movieDetailsFragmentViewModel: MovieDetailsFragmentViewModel by viewModels()
    private lateinit var categoryAdapter: DetailsCategoryAdapter
    private lateinit var languageAdapter: DetailsLanguageAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        movieDetailsFragmentViewModel?.getSelectedMovieDetail(arguments?.get("id") as Int)

        movieDetailsFragmentViewModel.selectedMovieLiveData.observe(viewLifecycleOwner, Observer {
            categoryAdapter.differ.submitList(it?.data?.genres)
            languageAdapter.differ.submitList(it?.data?.spoken_languages)
            Glide.with(this).load(IMG_URL + it.data?.poster_path).into(iv_details)
            tv_details_title.text = it.data?.title
            tv_details_runtime.text =  "Runtime: "+ it.data?.runtime.toString() + " Minutes"
            tv_details_rating.text = ((it.data!!.vote_average)/2).toString() +" "
            tv_details_synopsis.text = it.data?.overview
        })

        ib_details_back.setOnClickListener {
            findNavController().popBackStack()
        }

        ib_details_ticket.setOnClickListener {
            findNavController().navigate(R.id.action_movieDetailsFragment_to_bookingFragment)
        }

    }



    private fun setupRecyclerView()
    {
        categoryAdapter = DetailsCategoryAdapter()
        rv_details_category.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        }

        languageAdapter = DetailsLanguageAdapter()
        rv_details_language.apply {
            adapter = languageAdapter
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        }
    }

}