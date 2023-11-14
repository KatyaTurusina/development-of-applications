package com.example.randomnumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onNextActivity(view: View) {
        val intent = Intent(applicationContext, GameActivity::class.java)
        val left_number: EditText = findViewById(R.id.left)
        val right_number: EditText = findViewById(R.id.right)

        val left: Int = Integer.parseInt(left_number.text.toString())
        val right: Int = Integer.parseInt(right_number.text.toString())

        intent.putExtra("left", left)
        intent.putExtra("right", right)
        startActivity(intent)
    }
}
