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
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlinmovieapp.databinding.ActivityMainBinding
import com.example.kotlinmovieapp.model.Movie
import com.example.kotlinmovieapp.view.MovieAdapter
import com.example.kotlinmovieapp.view.PageAdapter
import com.example.kotlinmovieapp.view.PopularMoviesFragment
import com.example.kotlinmovieapp.view.TopRatedMoviesFragment
import com.example.kotlinmovieapp.view.UpcomingMoviesFragment
import com.example.kotlinmovieapp.viewmodel.MovieViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var viewPager : ViewPager2
    lateinit var adapter: PageAdapter
    lateinit var tabLayout : TabLayout
    val headers: List<String> = listOf("Popular","Top rated","Upcoming")


   /* private var movies = arrayListOf<Movie>()
    lateinit var recycle: RecyclerView
    lateinit var adapter: MovieAdapter
    lateinit var swipe: SwipeRefreshLayout
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MovieViewModel*/
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)

       /* binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        getPopularMovies()

        swipe = binding.swipe
        swipe.setColorSchemeColors(R.color.black)
        swipe.setOnRefreshListener { getPopularMovies() }
    }

    public fun getPopularMovies(){
        viewModel.getPopularMovies().observe(this) { moviesFD ->
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
    }*/




       tabLayout = findViewById(R.id.tabLayout)

       viewPager = findViewById(R.id.viewPager)
       viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

       adapter = PageAdapter(supportFragmentManager,lifecycle)
       adapter.addFragment(PopularMoviesFragment())
       adapter.addFragment(TopRatedMoviesFragment())
       adapter.addFragment(UpcomingMoviesFragment())

       viewPager.adapter = adapter

       TabLayoutMediator(
           tabLayout,viewPager
       ){
               tab,pos ->
           tab.text = headers.get(pos)
       }.attach()
   }
}