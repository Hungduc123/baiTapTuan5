package com.example.baitaptuan1.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.baitaptuan1.model.LoginTableModel

// room
@Dao
interface DAOAccess {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(loginTableModel: LoginTableModel)

    @Query("SELECT * FROM Login WHERE Email =:email AND Password =:password")
    fun getLoginDetails(email: String?, password: String) : LiveData<LoginTableModel>
}