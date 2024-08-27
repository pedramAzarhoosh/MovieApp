package com.example.kotlinmovieapp.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class PageAdapter(fm:FragmentManager,lc:Lifecycle) :FragmentStateAdapter(fm,lc) {
    var fragmentList : ArrayList<Fragment> = ArrayList()
    public fun addFragment(fragment: Fragment){
        fragmentList.add(fragment)
    }

    override fun getItemCount(): Int {
        //return the number of items in viewPager
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList.get(position)
    }
}