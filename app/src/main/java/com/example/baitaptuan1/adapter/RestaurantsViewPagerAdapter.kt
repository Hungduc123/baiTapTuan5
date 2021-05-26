package com.example.baitaptuan1.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.baitaptuan1.fragment.FavRestaurantFragment
import com.example.baitaptuan1.fragment.HomeRestaurantFragment


class RestaurantsViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeRestaurantFragment()
            1 -> FavRestaurantFragment()
            else -> HomeRestaurantFragment()
        }
    }
}
