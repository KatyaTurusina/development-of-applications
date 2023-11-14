package com.example.randomnumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class GameActivity : AppCompatActivity() {
    var left: Int = Int.MIN_VALUE
    var right: Int = Int.MAX_VALUE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        left = intent.getIntExtra("left", 0)
        right = intent.getIntExtra("right", 100)
        val tvText = findViewById<TextView>(R.id.text)
        tvText.text  = createText(left, right)
    }

    fun createText(left: Int, right: Int): String {
        if (right - left == 0) {
            val butY = findViewById<Button>(R.id.yes)
            val butN = findViewById<Button>(R.id.no)
            butY.isEnabled = false
            butN.isEnabled = false
            return "Ваше число $left"

        }
        val mean = (right + left) / 2
        return "Ваше число больше $mean ?"
    }

    fun onYesNoClick(view: View) {
        val tvText = findViewById<TextView>(R.id.text)
        when (view.id) {
            R.id.yes -> {
                left = (left + right) / 2 + 1
                tvText.text  = createText(left, right)
            }
            R.id.no -> {
                right = (left + right) / 2
                tvText.text  = createText(left, right)
            }
        }
    }
}
