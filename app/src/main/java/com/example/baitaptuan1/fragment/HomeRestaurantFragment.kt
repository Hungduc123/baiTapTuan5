package com.example.baitaptuan1.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
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
    lateinit var fav: MenuItem

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
        setHasOptionsMenu(true)

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
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        val newId = 100
        fav  = menu.add(0, newId, 0, "Grid")
        fav.setIcon(R.drawable.icongrid);
        fav.setShowAsAction (MenuItem.SHOW_AS_ACTION_ALWAYS)




    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item?.itemId) {
            100-> {
                if (layoutManager?.spanCount == 1) {
                    layoutManager?.spanCount = 3
                    item.title = "list"
//                    item.icon = context?.let { getDrawable(it,R.drawable.listicon) }
                    fav.setIcon(R.drawable.listicon);

//                            list.setImageDrawable();
                } else {
                    layoutManager?.spanCount = 1
                    item.title = "grid"
//                        item.icon=  icongrid
//                   item.icon = context?.let { getDrawable(it,R.drawable.icongrid) }
                    fav.setIcon(R.drawable.icongrid);

                }
                homerec.adapter?.notifyItemRangeChanged(0, homerec.adapter?.itemCount ?: 0)
            }

        }
        return super.onOptionsItemSelected(item)
    }

}