package com.example.themoviedbapplication.activity.discover_movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.common.entity.discover_movie.ResultDiscoverMovie
import com.example.themoviedbapplication.activity.movie_details.MovieDetailActivity
import com.example.themoviedbapplication.databinding.LayoutDiscoverActivityItemBinding


class DiscoverMoviePagingAdapter(
    val context: Context
): PagingDataAdapter<ResultDiscoverMovie, DiscoverMovieViewHolder>(
    itemCallback
) {
    override fun onBindViewHolder(holder: DiscoverMovieViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.data = data
        holder.binding.root.setOnClickListener{
            val selectedMovieId = data?.id
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("EXTRA_DATA",selectedMovieId)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverMovieViewHolder {
        return DiscoverMovieViewHolder(
            LayoutDiscoverActivityItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    companion object {
        val itemCallback = object : DiffUtil.ItemCallback<ResultDiscoverMovie>() {
            override fun areItemsTheSame(oldItem: ResultDiscoverMovie, newItem: ResultDiscoverMovie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ResultDiscoverMovie, newItem: ResultDiscoverMovie): Boolean {
                return true

            }
        }
    }
}

class DiscoverMovieViewHolder(
    val binding: LayoutDiscoverActivityItemBinding
) : RecyclerView.ViewHolder(binding.root)