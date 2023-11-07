package com.example.randomfilm

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Random

class MainActivity : AppCompatActivity() {

    lateinit var movies: Array<String>
    lateinit var seen_movies:BooleanArray
    val r = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movies = resources.getStringArray(R.array.movies)
        Log.d("mytag", "movies"+ movies.contentToString())
        seen_movies = BooleanArray(movies.size, {false})
    }

    fun onClick(view: View) {
        var b = findViewById<Button>(R.id.button1)
        b.text = "Следующий фильм"
        var index = r.nextInt(movies.size)
        // check massive seen_movies on index
        while (seen_movies[index]) {
            index = r.nextInt(movies.size)
        }
        seen_movies[index] = true
        onNextClick(index)
    }

    fun onNextClick(index: Int){
        val tvTitle = findViewById<TextView>(R.id.title)
        tvTitle.text = movies[index]
        if (checkAllSeen()) {
            tvTitle.text = "Фильмы закончились:("
        }
    }
    fun checkAllSeen(): Boolean {
        for (element in seen_movies) {
            if (!element) {
                return false
            }
        }
        return true
    }
}
