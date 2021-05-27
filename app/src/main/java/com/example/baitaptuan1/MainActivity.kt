package com.example.baitaptuan1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.baitaptuan1.fragment.OnBoardingOneFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        supportFragmentManager.commit { setReorderingAllowed(true)
            add<OnBoardingOneFragment>(R.id.frag_container_view)}



    }
}