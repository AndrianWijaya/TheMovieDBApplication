package com.example.themoviedbapplication.activity.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.common.entity.genre.Genre
import com.example.themoviedbapplication.databinding.LayoutGenreItemBinding

class GenreAdapter(
    val startSupportActionModeClick: (Genre) -> Unit,
    val getSelection: () -> List<Genre>,
    val toggleClick:(Genre)->Unit
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
        holder.binding.isSelected = getSelection().contains(data)
        holder.binding.root.setOnLongClickListener {
            startSupportActionModeClick(data)
            true
        }
        holder.binding.root.setOnClickListener {
            toggleClick(data)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun submitData(
        data: List<Genre>
    ) {
        differ.submitList(data)
    }

    fun clearSelection(changes: (() -> Unit)?){

        val toggleDiffUtil = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = differ.currentList.size

            override fun getNewListSize(): Int = differ.currentList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = true

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return !getSelection().contains(differ.currentList[oldItemPosition])
            }
        }
        val differ = DiffUtil.calculateDiff(toggleDiffUtil)
        changes?.invoke()
        differ.dispatchUpdatesTo(this)
    }


    fun toggleSelection(genre: Genre, changes: () -> Unit) {
        val toggleDiffUtil = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = differ.currentList.size

            override fun getNewListSize(): Int = differ.currentList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = true

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return differ.currentList[oldItemPosition] != genre
            }
        }
        val differ = DiffUtil.calculateDiff(toggleDiffUtil)
        changes()
        differ.dispatchUpdatesTo(this)
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