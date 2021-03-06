package com.example.baitaptuan1.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.baitaptuan1.model.LoginTableModel
import com.example.baitaptuan1.repository.LoginRepository


class LoginViewModel : ViewModel() {
    var liveDataLogin: LiveData<LoginTableModel>? = null

    fun insertData(context: Context, email: String, password: String, fullname: String) {
        LoginRepository.insertData(context, email, password, fullname)
    }

    fun getLoginDetails(context: Context, email: String, password: String): LiveData<LoginTableModel>? {
        liveDataLogin = LoginRepository.getLoginDetails(context, email, password)
        return liveDataLogin
    }

}