package com.example.snowflakes2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // заменим разметку на нашу View
        //setContentView(R.layout.activity_main)
        setContentView(Snowflakes(this))
    }
}
