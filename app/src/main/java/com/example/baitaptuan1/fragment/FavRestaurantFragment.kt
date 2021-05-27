package com.example.baitaptuan1.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baitaptuan1.ListRestaurantsActivity
import com.example.baitaptuan1.R
import com.example.baitaptuan1.adapter.FavoriteListAdapter
import com.example.baitaptuan1.model.FavoriteList


class FavRestaurantFragment : Fragment() {

    private lateinit var recycleView: RecyclerView
    private var layoutManager: GridLayoutManager? = null
    lateinit var favoriteLists : List<FavoriteList>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        favoriteLists = ListRestaurantsActivity.favoriteDatabase!!.favoriteDao()!!.getFavoriteData() as List<FavoriteList>



    }
    override fun onResume(){
        super.onResume()
        favoriteLists = ListRestaurantsActivity.favoriteDatabase!!.favoriteDao()!!.getFavoriteData() as List<FavoriteList>
        val adapter = FavoriteListAdapter(favoriteLists, requireActivity().applicationContext);
        recycleView.adapter = adapter

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v : View = inflater.inflate(R.layout.fragment_fav_restaurant, container, false)
        recycleView = v.findViewById(R.id.favrec)
        recycleView.setHasFixedSize(true)
        recycleView.layoutManager = LinearLayoutManager(v.context)
        val adapter = FavoriteListAdapter(favoriteLists, requireActivity().applicationContext);
        recycleView.adapter = adapter
        return v
    }

}