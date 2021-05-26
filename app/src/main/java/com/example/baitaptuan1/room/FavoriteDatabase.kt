package com.example.baitaptuan1.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.baitaptuan1.model.FavoriteList

@Database(entities = [FavoriteList::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao?

}
