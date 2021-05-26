package com.example.baitaptuan1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import androidx.viewpager2.widget.ViewPager2
import com.example.baitaptuan1.adapter.RestaurantsViewPagerAdapter
import com.example.baitaptuan1.databinding.ActivityListRestaurantsBinding
import com.example.baitaptuan1.room.FavoriteDatabase
import java.io.IOException
import java.nio.charset.Charset

class ListRestaurantsActivity : AppCompatActivity() {
    lateinit var binding : ActivityListRestaurantsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.teal_700)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_list_restaurants)
        val adapter = RestaurantsViewPagerAdapter(this)
        binding.viewPager.adapter = adapter

        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                when(position){
                    0->binding.bottonNavigation.menu.findItem(R.id.navigation_home).setChecked(true)
                    1->{binding.bottonNavigation.menu.findItem(R.id.navigation_fav).setChecked(true)
                    }
                    else-> {
                        0
                    }
                }
            }
        })
        binding.bottonNavigation.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> binding.viewPager.setCurrentItem(0)
                R.id.navigation_fav ->{
                    binding.viewPager.setCurrentItem(1)
                }

            }
        }
        ListRestaurantsActivity.Companion.favoriteDatabase = Room.databaseBuilder(
            applicationContext,
            FavoriteDatabase::class.java, "myfavdb"
        ).allowMainThreadQueries().build()


    }
    fun getJSONFromAssets(): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8

        try {
            val myRestaurantsJSONFILE = assets.open("Restaurants.json")
            val size = myRestaurantsJSONFILE.available()
            val buffer = ByteArray(size)
            myRestaurantsJSONFILE.read(buffer)
            myRestaurantsJSONFILE.close()
            json = String(buffer, charset)

        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
    companion object {
        var favoriteDatabase: FavoriteDatabase? = null
    }
    }
