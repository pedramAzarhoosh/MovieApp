package com.example.kotlinmovieapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemAnimator
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.kotlinmovieapp.databinding.ActivityMainBinding
import com.example.kotlinmovieapp.model.Movie
import com.example.kotlinmovieapp.view.MovieAdapter
import com.example.kotlinmovieapp.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {
    private var movies = arrayListOf<Movie>()
    lateinit var recycle: RecyclerView
    lateinit var adapter: MovieAdapter
    lateinit var swipe: SwipeRefreshLayout
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MovieViewModel
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        getPopularMovies()

        swipe = binding.swipe
        swipe.setColorSchemeColors(R.color.black)
        swipe.setOnRefreshListener { getPopularMovies() }
    }

    public fun getPopularMovies(){
        viewModel.getMovies().observe(this) { moviesFD ->
            movies = ArrayList(moviesFD)
            displayPopularMovies()
        }
    }

    fun displayPopularMovies(){
        recycle = binding.recycle
        adapter = MovieAdapter(this,movies)
        recycle.adapter = adapter
        recycle.layoutManager = LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
    }
}