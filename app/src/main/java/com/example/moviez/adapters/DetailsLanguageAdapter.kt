package com.example.moviez.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviez.R
import com.example.moviez.model.MovieDetails.Genre
import com.example.moviez.model.MovieDetails.SpokenLanguage
import kotlinx.android.synthetic.main.cv_item_small.view.*

class DetailsLanguageAdapter: RecyclerView.Adapter<DetailsLanguageAdapter.DetailsLanguageViewHolder>() {

    inner class DetailsLanguageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<SpokenLanguage>()
    {
        override fun areItemsTheSame(
            oldItem: SpokenLanguage,
            newItem: SpokenLanguage
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SpokenLanguage,
            newItem: SpokenLanguage
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsLanguageViewHolder {
        return DetailsLanguageViewHolder(
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

    override fun onBindViewHolder(holder: DetailsLanguageAdapter.DetailsLanguageViewHolder, position: Int) {
        val language = differ.currentList[position]
        holder.itemView.apply {
            tv_moviez_category.text = language.english_name

            setOnClickListener {
                onItemClickListener?.let { it(language) }
            }
        }
    }

    private var onItemClickListener: ((SpokenLanguage) -> Unit)? = null

    fun setOnItemClickListener(listener: (SpokenLanguage) -> Unit) {
        onItemClickListener = listener
    }


}