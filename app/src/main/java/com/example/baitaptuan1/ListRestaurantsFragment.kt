package com.example.baitaptuan1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.baitaptuan1.databinding.ActivityListRestaurantBinding
import com.example.baitaptuan1.restaurant.Restaurant
import com.example.baitaptuan1.restaurant.RestaurantAdapter
import com.example.baitaptuan1.restaurant.RestaurantViewModel
import com.example.baitaptuan1.restaurant.getDataSet
import kotlinx.android.synthetic.main.activity_list_restaurant.*


class ListRestaurantsFragment : Fragment() {
    private lateinit var binding: ActivityListRestaurantBinding
    private lateinit var viewModel: RestaurantViewModel
    private var layoutManager: GridLayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_restaurants, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        viewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)






        rcList.adapter = RestaurantAdapter(layoutManager as GridLayoutManager)

        val adapter = RestaurantAdapter();
        binding.rcList.adapter = adapter
        adapter.listener = object : RestaurantAdapter.RestaurantAdapterListener {
            override fun onClickItem(restaurant: Restaurant) {
                TODO("Not yet implemented")
                Log.e(">>>>>", "name: ${restaurant.name}")

            }
        }
        adapter.submitList(getDataSet())
        }




}