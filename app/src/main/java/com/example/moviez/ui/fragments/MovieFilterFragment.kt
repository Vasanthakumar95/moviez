package com.example.moviez.ui.fragments

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver.OnScrollChangedListener
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviez.R
import com.example.moviez.adapters.MovieListAdapter
import com.example.moviez.ui.viewModel.MovieFilterFragmentViewModel
import com.example.moviez.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_filter.*
import java.lang.NullPointerException


@AndroidEntryPoint
class MovieFilterFragment : Fragment(R.layout.fragment_movie_filter) {

    private val movieFilterFragmentViewModel: MovieFilterFragmentViewModel by viewModels()
    private lateinit var movieListAdapter: MovieListAdapter
    private val initial_page: Int = 1
    private var current_page: Int = 0
    private var total_page: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        filter_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("Nothing Selected")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                current_page = initial_page
                when(position)
                {
                    0 -> movieFilterFragmentViewModel.getListByLatestRelease(initial_page)
                    1 -> movieFilterFragmentViewModel.getListByA_Z(initial_page)
                    2 -> movieFilterFragmentViewModel.getListByRating(initial_page)
                }
            }
        }

        srl_filter.setOnRefreshListener {
            current_page = initial_page
            when(filter_spinner.selectedItemPosition)
            {
                0 -> movieFilterFragmentViewModel.getListByLatestRelease(initial_page)
                1 -> movieFilterFragmentViewModel.getListByA_Z(initial_page)
                2 -> movieFilterFragmentViewModel.getListByRating(initial_page)
            }

                srl_filter.isRefreshing = false
        }


        movieFilterFragmentViewModel.movieListLiveData.observe(viewLifecycleOwner, Observer {
            when(it)
            {
                is Resource.Success->
                {
                    it.data?.let{
                        movieListAdapter.differ.submitList(it.results)
                        total_page = it.total_pages
                        current_page = it.page
                    }
                }
            }
            movieFilterFragmentViewModel.movieListLiveData.removeObserver{}
        })

        rv_filter.getViewTreeObserver()
            .addOnScrollChangedListener(OnScrollChangedListener {
                try {
                    if (!rv_filter.canScrollVertically(1)) {

                        when(filter_spinner.selectedItemPosition)
                        {
                            0 -> movieFilterFragmentViewModel.getListByLatestRelease(current_page+initial_page)
                            1 -> movieFilterFragmentViewModel.getListByA_Z(current_page+initial_page)
                            2 -> movieFilterFragmentViewModel.getListByRating(current_page+initial_page)
                        }

                        movieFilterFragmentViewModel.movieListLiveData.observe(viewLifecycleOwner, Observer {

                            movieListAdapter.differ.submitList(it.data?.results)
                            current_page = it.data!!.page

                        })

                        Toast.makeText(this.context, (current_page+1).toString()+"/$total_page", Toast.LENGTH_SHORT).show()
                    }
                    if (!rv_filter.canScrollVertically(-1)) {
                        // top of scroll view
                    }
                }catch (e: NullPointerException)
                {

                }

            })


        movieListAdapter.setOnItemClickListener {
            var bundle = Bundle()
            bundle.putInt("id", it.id)
            findNavController().navigate(R.id.action_movieFilterFragment_to_movieDetailsFragment, bundle)
        }

        iv_filter_back.setOnClickListener {
            findNavController().navigate(R.id.action_movieFilterFragment_to_homeFragment)
        }

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            view.context,
            R.array.filters_types,
            R.layout.spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.spinner_item)
            // Apply the adapter to the spinner
            filter_spinner.adapter = adapter
        }
    }


    private fun setupRecyclerView()
    {
        movieListAdapter = MovieListAdapter()
        rv_filter.apply {
            adapter = movieListAdapter
            layoutManager = GridLayoutManager(activity, 2 ,RecyclerView.VERTICAL, false)
        }
    }
}