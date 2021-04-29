package com.example.baitaptuan1

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Onboarding_one : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_one)
        supportActionBar?.hide();

        val imgNextOnboaringOne=findViewById<ImageView>(R.id.imgNextOnboaringOne);
        imgNextOnboaringOne.setOnClickListener{
            val intent = Intent(this, onboaring_two::class.java)
            startActivity(intent)
        }



    }
}