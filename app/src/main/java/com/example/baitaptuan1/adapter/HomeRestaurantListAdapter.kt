package com.example.baitaptuan1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.baitaptuan1.ListRestaurantsActivity
import com.example.baitaptuan1.R
import com.example.baitaptuan1.model.FavoriteList
import com.example.baitaptuan1.model.Restaurant

//HomeRestaurantListAdapter
class HomeRestaurantListAdapter(private val layoutManager: GridLayoutManager? = null) : ListAdapter<Restaurant, HomeRestaurantListAdapter.ViewHolder>(RestaurantDiffUtilCallback()) {

    lateinit var context: Context
    var restaurant: List<Restaurant>? = null

    enum class ViewType {
        SMALL,
        DETAILED
    }
    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == 1) HomeRestaurantListAdapter.ViewType.DETAILED.ordinal
        else HomeRestaurantListAdapter.ViewType.SMALL.ordinal
    }

    class RestaurantDiffUtilCallback : DiffUtil.ItemCallback<Restaurant>(){
        override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem.Id == newItem.Id
        }

        override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem == newItem
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvRestaurantName = itemView.findViewById<TextView>(R.id.itemTitle)
        val tvRestaurantAddress = itemView.findViewById<TextView>(R.id.itemDetail)
        val imgRestaurant = itemView.findViewById<ImageView>(R.id.itemImage)
        val favBtn = itemView.findViewById<ImageView>(R.id.fav_btn)

        fun bind(item: Restaurant) {
            tvRestaurantName.text = item.Name
            tvRestaurantAddress.text = item.Address
            Glide.with(itemView.context)
                .load(item.PicturePath)
                .into(imgRestaurant)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = getItem(position)
        viewHolder.bind(item)
        if (ListRestaurantsActivity.favoriteDatabase!!.favoriteDao()!!.isFavorite(item.Id) === 1
        ) viewHolder.favBtn.setImageResource(R.drawable.ic_favorite) else viewHolder.favBtn.setImageResource(
            R.drawable.ic_favorite_border_black_24dp
        )
        viewHolder.favBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val favoriteList = FavoriteList()
                val id: Int = item.Id
                val image: String = item.PicturePath
                val name: String = item.Name
                val address: String = item.Address
                favoriteList.Id = id
                favoriteList.Name = name
                favoriteList.Address = address
                favoriteList.PicturePath = image
                if (ListRestaurantsActivity.favoriteDatabase!!.favoriteDao()!!.isFavorite(id) !== 1) {
                    viewHolder.favBtn.setImageResource(R.drawable.ic_favorite)
                    ListRestaurantsActivity.favoriteDatabase!!.favoriteDao()!!.addData(favoriteList)
                } else {
                    viewHolder.favBtn.setImageResource(R.drawable.ic_favorite_border_black_24dp)
                    ListRestaurantsActivity.favoriteDatabase!!.favoriteDao()!!.delete(favoriteList)
                }
            }
        })
    }

}