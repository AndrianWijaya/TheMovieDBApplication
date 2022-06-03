package com.example.themoviedbapplication.activity.genre

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.common.entity.genre.Genre
import com.example.themoviedbapplication.activity.discover_movie.DiscoverMovieActivity
import com.example.themoviedbapplication.databinding.LayoutGenreItemBinding

class GenreAdapter(
    val context: Context
) : RecyclerView.Adapter<GenreAdapterViewHolder>() {
    val differ = AsyncListDiffer<Genre>(this, itemCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreAdapterViewHolder {
        return GenreAdapterViewHolder(
            LayoutGenreItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: GenreAdapterViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.binding.data = data
        holder.binding.root.setOnClickListener() {
            val genreId = data?.id
            val intent = Intent(context, DiscoverMovieActivity::class.java)
            intent.putExtra("EXTRA_DATA", genreId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun submitData(
        data: List<Genre>
    ) {
        differ.submitList(data)
    }

    companion object {
        val itemCallback = object : DiffUtil.ItemCallback<Genre>() {
            override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
                return true
            }

        }
    }
}


class GenreAdapterViewHolder(
    val binding: LayoutGenreItemBinding
) : RecyclerView.ViewHolder(binding.root)