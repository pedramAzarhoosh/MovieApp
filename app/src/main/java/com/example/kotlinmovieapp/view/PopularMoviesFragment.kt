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


class PopularMoviesFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_popular_movies, container, false)

        recycle = view.findViewById(R.id.recycle)

        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        getPopularMovies()

        swipe = view.findViewById(R.id.swipe)
        swipe.setColorSchemeColors(R.color.black)
        swipe.setOnRefreshListener { getPopularMovies() }

        return view
    }

    public fun getPopularMovies(){
        viewModel.getPopularMovies().observe(requireActivity()) { moviesFD ->
            movies = ArrayList(moviesFD)
            displayPopularMovies()
        }
    }

    fun displayPopularMovies(){
        adapter = MovieAdapter(requireActivity(),movies)
        recycle.adapter = adapter
        recycle.layoutManager = LinearLayoutManager(requireActivity())
        adapter.notifyDataSetChanged()
    }

}