package com.example.baitaptuan1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.baitaptuan1.R
import com.example.baitaptuan1.model.FavoriteList

class FavoriteListAdapter(private val favoriteLists: List<FavoriteList>, context: Context) :  RecyclerView.Adapter<FavoriteListAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvRestaurantName = itemView.findViewById<TextView>(R.id.itemTitle)
        val tvRestaurantAddress = itemView.findViewById<TextView>(R.id.itemDetail)
        val imgRestaurant = itemView.findViewById<ImageView>(R.id.itemImage)

        fun bind(item: FavoriteList) {
            tvRestaurantName.text = item.Name
            tvRestaurantAddress.text = item.Address
            Glide.with(itemView.context)
                .load(item.PicturePath)
                .into(imgRestaurant)
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteListAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant,parent,false)
        return FavoriteListAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteListAdapter.ViewHolder, position: Int) {
        val item = favoriteLists[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return favoriteLists.size
    }
}