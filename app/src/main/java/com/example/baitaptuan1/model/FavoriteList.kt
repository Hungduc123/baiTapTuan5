package com.example.baitaptuan1.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoritelist")
class FavoriteList {
    @PrimaryKey
    var Id = 0

    @ColumnInfo(name = "prname")
    var Name: String? = null

    @ColumnInfo(name = "address")
    var Address: String? = null

    @ColumnInfo(name = "image")
    var PicturePath: String? = null


}