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


class UpcomingMoviesFragment : Fragment() {
    private var movies = arrayListOf<Movie>()
    lateinit var recycle: RecyclerView
    lateinit var adapter: MovieAdapter
    lateinit var swipe: SwipeRefreshLayout
    lateinit var viewModel: MovieViewModel

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_upcoming_movies, container, false)

        recycle = view.findViewById(R.id.upcomingRecycle)

        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        getUpcomingMovies()

        swipe = view.findViewById(R.id.upcomingSwipe)
        swipe.setColorSchemeColors(R.color.black)
        swipe.setOnRefreshListener { getUpcomingMovies() }

        return view
    }


    public fun getUpcomingMovies(){
        viewModel.getUpcomingMovies().observe(requireActivity()) { moviesFD ->
            movies = ArrayList(moviesFD)
            displayUpcomingdMovies()
        }
    }

    fun displayUpcomingdMovies(){
        adapter = MovieAdapter(requireActivity(),movies)
        recycle.adapter = adapter
        recycle.layoutManager = LinearLayoutManager(requireActivity())
        adapter.notifyDataSetChanged()
    }

}