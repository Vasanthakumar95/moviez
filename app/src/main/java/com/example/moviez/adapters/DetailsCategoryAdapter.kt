package com.example.moviez.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviez.R
import com.example.moviez.model.MovieDetails.Genre
import kotlinx.android.synthetic.main.cv_item_small.view.*

class DetailsCategoryAdapter: RecyclerView.Adapter<DetailsCategoryAdapter.DetailsCategoryViewHolder>() {

    inner class DetailsCategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Genre>()
    {
        override fun areItemsTheSame(
            oldItem: Genre,
            newItem: Genre
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Genre,
            newItem: Genre
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsCategoryViewHolder {
        return DetailsCategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cv_item_small,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: DetailsCategoryAdapter.DetailsCategoryViewHolder, position: Int) {
        val category = differ.currentList[position]
        holder.itemView.apply {
            tv_moviez_category.text = category.name

            setOnClickListener {
                onItemClickListener?.let { it(category) }
            }
        }
    }

    private var onItemClickListener: ((Genre) -> Unit)? = null

    fun setOnItemClickListener(listener: (Genre) -> Unit) {
        onItemClickListener = listener
    }


}