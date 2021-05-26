package com.example.baitaptuan1.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.baitaptuan1.model.FavoriteList

@Dao
interface FavoriteDao {
    @Insert
    fun addData(favoriteList: FavoriteList?)

    @Query("select * from favoritelist")
    fun getFavoriteData(): List<FavoriteList?>?

    @Query("SELECT EXISTS (SELECT 1 FROM favoritelist WHERE id=:id)")
    fun isFavorite(id: Int): Int

    @Delete
    fun delete(favoriteList: FavoriteList?)
}