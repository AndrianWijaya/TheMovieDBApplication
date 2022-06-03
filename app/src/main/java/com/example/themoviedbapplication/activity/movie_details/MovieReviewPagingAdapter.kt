package com.example.themoviedbapplication.activity.movie_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.common.entity.movie_reviews.Review
import com.example.themoviedbapplication.databinding.LayoutReviewItemBinding


class MovieReviewPagingAdapter: PagingDataAdapter<Review, MovieReviewViewHolder>(itemCallback) {
    override fun onBindViewHolder(holder: MovieReviewViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewViewHolder {
        return MovieReviewViewHolder(
            LayoutReviewItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    companion object{
        val itemCallback = object : DiffUtil.ItemCallback<Review>(){
            override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
                return true
            }

        }
    }
}

class MovieReviewViewHolder(
   val binding : LayoutReviewItemBinding
) : RecyclerView.ViewHolder(binding.root)