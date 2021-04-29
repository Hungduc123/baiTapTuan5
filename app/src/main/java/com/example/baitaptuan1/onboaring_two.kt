package com.example.baitaptuan1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_onboaring_two.*

class onboaring_two : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboaring_two)
        supportActionBar?.hide();

        nextOn2.setOnClickListener{
            val intent = Intent(this, onboaring_three::class.java)
            startActivity(intent)
        }
    }
}