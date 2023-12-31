package com.amit.movieguideapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.amit.movieguideapp.R
import com.amit.movieguideapp.databinding.ItemMovieBinding
import com.amit.movieguideapp.models.entity.Movie
import com.amit.movieguideapp.view.ui.common.AppExecutors
import com.amit.movieguideapp.view.ui.common.DataBoundListAdapter

class MovieListAdapter(
    appExecutors: AppExecutors,
    private val dataBindingComponent: DataBindingComponent,
    private val movieOnClickCallback: ((Movie) -> Unit)?
) : DataBoundListAdapter<Movie, ItemMovieBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
    }
) {

    override fun createBinding(parent: ViewGroup): ItemMovieBinding {
        val binding = DataBindingUtil.inflate<ItemMovieBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_movie,
                parent,
                false,
                dataBindingComponent
        )
        binding.root.setOnClickListener {
            binding.movie?.let {
//                movieOnClickCallback?.invoke(it)
            }
        }

        return binding
    }

    override fun bind(binding: ItemMovieBinding, item: Movie) {
        binding.movie = item
    }
}