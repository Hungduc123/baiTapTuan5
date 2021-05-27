package com.example.baitaptuan1.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baitaptuan1.ListRestaurantsActivity
import com.example.baitaptuan1.R
import com.example.baitaptuan1.adapter.HomeRestaurantListAdapter
import com.example.baitaptuan1.databinding.FragmentHomeRestaurantBinding
import com.example.baitaptuan1.model.Restaurant
import kotlinx.android.synthetic.main.fragment_home_restaurant.*
import org.json.JSONException
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeRestaurantFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeRestaurantFragment : Fragment() {

    private lateinit var recycleView: RecyclerView
    var restaurantList : ArrayList<Restaurant> = ArrayList()
    private lateinit var binding: FragmentHomeRestaurantBinding
    private var layoutManager: GridLayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v : View = inflater.inflate(R.layout.fragment_home_restaurant, container, false)
        recycleView = v.findViewById(R.id.homerec)
        layoutManager = GridLayoutManager(v.context, 1)
        recycleView.layoutManager = layoutManager
        recycleView.adapter = HomeRestaurantListAdapter(layoutManager as GridLayoutManager)
        val adapter = HomeRestaurantListAdapter();
        recycleView.adapter = adapter
        adapter.submitList(restaurantList)
        return v
        //return inflater.inflate(R.layout.fragment_home_restaurant, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            val obj = JSONObject((activity as ListRestaurantsActivity).getJSONFromAssets())
            val restaurantsArray = obj.getJSONArray("restaurants")

            for (i in 0 until restaurantsArray.length()) {

                val restaurant = restaurantsArray.getJSONObject(i)

                val id = restaurant.getInt("Id")
                val name = restaurant.getString("Name")
                val address=restaurant.getString("Address")
                val picturepath = restaurant.getString("PicturePath")
                val restaurantDetails =
                    Restaurant(id, name, address, picturepath)

                restaurantList.add(restaurantDetails)
            }
        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }

    }
}