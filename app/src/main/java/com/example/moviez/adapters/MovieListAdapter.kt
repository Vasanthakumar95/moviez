package com.example.moviez.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviez.R
import com.example.moviez.model.MovieList.Result
import com.example.moviez.util.Constants.Companion.IMG_URL
import kotlinx.android.synthetic.main.cv_item_big.view.*

class MovieListAdapter: RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    inner class MovieListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Result>()
    {
        override fun areItemsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cv_item_big,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MovieListAdapter.MovieListViewHolder, position: Int) {
        val result = differ.currentList[position]
        holder.itemView.apply {

            Glide.with(this).load(IMG_URL + result?.poster_path).into(cv_item_big_img)
            cv_item_big_title.text = result.original_title.capitalize()
            cv_item_big_rb.rating = ((result.vote_average)/2).toFloat()

            setOnClickListener {
                onItemClickListener?.let { it(result) }
            }
        }
    }

    private var onItemClickListener: ((Result) -> Unit)? = null

    fun setOnItemClickListener(listener: (Result) -> Unit) {
        onItemClickListener = listener
    }


}