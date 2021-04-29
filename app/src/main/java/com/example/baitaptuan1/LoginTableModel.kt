package com.example.baitaptuan1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Model Class
@Entity(tableName = "Login")
data class LoginTableModel(


        @ColumnInfo(name = "email")
        var Email: String,

        @ColumnInfo(name = "password")
        var Password: String,
        @ColumnInfo(name = "fullname")
        var FullName: String


) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null
}
