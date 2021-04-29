package com.example.baitaptuan1.restaurant

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
import com.example.baitaptuan1.R

class RestaurantAdapter(private val layoutManager: GridLayoutManager? = null) : ListAdapter<Restaurant,RestaurantAdapter.ViewHolder>(RestaurantDiffUtilCallback()) {
    enum class ViewType {
        SMALL,
        DETAILED
    }
    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == 1) RestaurantAdapter.ViewType.DETAILED.ordinal
        else RestaurantAdapter.ViewType.SMALL.ordinal
    }
    interface RestaurantAdapterListener{
        fun onClickItem(restaurant: Restaurant)
    }
    var listener : RestaurantAdapterListener? = null

    class RestaurantDiffUtilCallback : DiffUtil.ItemCallback<Restaurant>(){
        override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, listener)
    }

    class ViewHolder private constructor(val itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvRestaurantName = itemView.findViewById<TextView>(R.id.itemTitle)
        val tvRestaurantAddress = itemView.findViewById<TextView>(R.id.itemDetail)
        val imgRestaurant = itemView.findViewById<ImageView>(R.id.itemImage)

        companion object {
            fun from(parent: ViewGroup) : ViewHolder {
                var layoutInflater = LayoutInflater.from(parent.context)
                var view = layoutInflater.inflate(R.layout.itemrestaurants, parent, false)
                return ViewHolder(view)
            }
        }

        fun bind(item: Restaurant, listener: RestaurantAdapterListener?) {
            tvRestaurantName.text = item.name
            tvRestaurantAddress.text = item.address
            Glide.with(itemView.context)
                .load(item.picturePath)
                .into(imgRestaurant)

            itemView.setOnClickListener {
                listener?.onClickItem(item)
            }
        }

    }

}


