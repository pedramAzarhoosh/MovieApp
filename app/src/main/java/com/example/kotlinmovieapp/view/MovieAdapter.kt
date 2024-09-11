package com.example.kotlinmovieapp.view

import com.example.kotlinmovieapp.databinding.MovieListItemBinding
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinmovieapp.R
import com.example.kotlinmovieapp.model.Movie


class MovieAdapter(private val context: Context, private val moviesArrayList: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: MovieListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.movie_list_item, parent, false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = moviesArrayList[position]
        holder.movieListItemBinding.movie = movie
    }

    override fun getItemCount(): Int {
        return moviesArrayList.size
    }

    inner class MovieViewHolder(val movieListItemBinding: MovieListItemBinding) :
        RecyclerView.ViewHolder(movieListItemBinding.root) {
        init {
            movieListItemBinding.root.setOnClickListener {

            }
        }
    }
}
