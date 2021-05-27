package com.example.baitaptuan1.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baitaptuan1.ListRestaurantsActivity
import com.example.baitaptuan1.R
import com.example.baitaptuan1.adapter.FavoriteListAdapter
import com.example.baitaptuan1.model.FavoriteList
import kotlinx.android.synthetic.main.fragment_fav_restaurant.*


class FavRestaurantFragment : Fragment() {

    private lateinit var recycleView: RecyclerView
    private var layoutManager: GridLayoutManager? = null
    lateinit var favoriteLists : List<FavoriteList>
    lateinit var fav: MenuItem



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

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
                favrec.adapter?.notifyItemRangeChanged(0, favrec.adapter?.itemCount ?: 0)
            }

        }
        return super.onOptionsItemSelected(item)
    }

}