package com.example.themoviedbapplication.activity.genre

import android.app.ProgressDialog
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.view.ActionMode
import com.example.common.entity.base_response.AppResponse
import com.example.common.entity.genre.Genre
import com.example.common.ext.clear
import com.example.common.ext.isEmpty
import com.example.common.ext.size
import com.example.common.ext.toggle
import com.example.themoviedbapplication.activity.discover_movie.DiscoverMovieActivity

var actionMode: ActionMode? = null

fun GenreActivity.observeLiveData()= with(vm) {
    binding.recycler.adapter = adapter

    var dialog : ProgressDialog? = null
    data.observe(this@observeLiveData){
        when(it.state){
            AppResponse.ERROR ->{
                dialog?.dismiss()
                Toast.makeText(this@observeLiveData, "Network Error(${it.code.toString()})",
                    Toast.LENGTH_SHORT).show()
            }
            AppResponse.SUCCESS -> {
                adapter.submitData(it.data.orEmpty())
                dialog?.dismiss()
            }
            AppResponse.LOADING ->{
                dialog?.dismiss()
                dialog = ProgressDialog(this@observeLiveData).apply {
                    setCancelable(false)
                    setProgressStyle(ProgressDialog.STYLE_SPINNER)
                    setMessage("Memproses ..")
                    show()
                }
            }
        }

    }
    selection.observe(this@observeLiveData) {
        actionMode?.let {
            it.title = "${vm.selection.size()} selected"
        } ?: kotlin.run {
            actionMode = startSupportActionMode(object : ActionMode.Callback {
                override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    println()
                    return true
                }

                override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    println()
                    return true
                }

                override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                    println()
                    return true
                }

                override fun onDestroyActionMode(mode: ActionMode?) {
                    val sel = vm.selection
                    if(!sel.isEmpty()){
                        adapter.clearSelection { vm.selection.clear() }
                    }
                    actionMode = null
                    println()
                }

            })
            actionMode?.title = "${vm.selection.size()} selected"
        }
        if (it.isEmpty()) {
            adapter.clearSelection{
                actionMode?.finish()
                actionMode = null
            }
        }
    }
}


fun GenreActivity.initBinding() = with(vm){
    binding.recycler.adapter = adapter
    binding.nextButton.setOnClickListener{
        val intent = Intent(this@initBinding, DiscoverMovieActivity::class.java)
        selection.value?.let {
            val list = arrayListOf<Int>()
            it.map {
                list.add(it.id)
            }
            intent.putIntegerArrayListExtra("EXTRA_DATA", list)
            startActivity(intent)
        }
    }

    binding.retry.setOnClickListener{
        getGenre()
    }
}

fun GenreActivity.toggleClick(genre: Genre) {
    adapter.toggleSelection(genre) {
        vm.selection.toggle(genre)
    }

}

fun GenreActivity.startActionMode(genre: Genre) {
    adapter.toggleSelection(genre) {
        vm.selection.toggle(genre)
    }
}