package com.example.kotlinmovieapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.kotlinmovieapp.R
import com.example.kotlinmovieapp.model.Movie
import com.example.kotlinmovieapp.viewmodel.MovieViewModel


class TopRatedMoviesFragment : Fragment() {

    private var movies = arrayListOf<Movie>()
    lateinit var recycle: RecyclerView
    lateinit var adapter: MovieAdapter
    lateinit var swipe: SwipeRefreshLayout
    lateinit var viewModel: MovieViewModel

    @SuppressLint("MissingInflatedId", "ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_top_rated_movies, container, false)

        recycle = view.findViewById(R.id.topRatedRecycle)

        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        getTopRatedMovies()

        swipe = view.findViewById(R.id.topRatedSwipe)
        swipe.setColorSchemeColors(R.color.black)
        swipe.setOnRefreshListener { getTopRatedMovies() }

        return view
    }

    public fun getTopRatedMovies(){
        viewModel.getTopRatedMovies().observe(requireActivity()) { moviesFD ->
            movies = ArrayList(moviesFD)
            displayTopRatedMovies()
        }
    }

    fun displayTopRatedMovies(){
        adapter = MovieAdapter(requireActivity(),movies)
        recycle.adapter = adapter
        recycle.layoutManager = LinearLayoutManager(requireActivity())
        adapter.notifyDataSetChanged()
    }

}