package com.example.randomfilm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.google.gson.Gson
import java.io.InputStreamReader
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var movies : Array<Movie>
    lateinit var seenMovies: Array<Boolean>
    val r = Random(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movies_stream = resources.openRawResource(R.raw.movies)
        val gson = Gson()
        val movies_data = gson.fromJson(InputStreamReader(movies_stream), Movies::class.java)
        Log.d("mytag", "Loaded movies ${movies_data.movies.size}")
        Log.d("mytag", movies_data.movies[0].name)
        movies = movies_data.movies
        seenMovies = Array<Boolean> (movies.size) { false }
    }

    fun onNextClick(view: View) {
        val title = findViewById<TextView>(R.id.title)
        val name = findViewById<TextView>(R.id.name)
        val genre = findViewById<TextView>(R.id.genre)
        val year = findViewById<TextView>(R.id.year)
        val rating = findViewById<TextView>(R.id.rating)
        val country = findViewById<TextView>(R.id.country)
        val main_roles = findViewById<ListView>(R.id.main_roles)
        val producer = findViewById<TextView>(R.id.producer)
        while (true) {
            val index = r.nextInt(movies.size)
            if (false !in seenMovies) {
                title.text = "Фильмов больше нет"
                name.text = "-"
                year.text = "-"
                genre.text = "-"
                rating.text = "-"
                country.text = "-"
                main_roles.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayOf("-"))
                producer.text = "-"
                break
            }
            if (!seenMovies[index]) {
                name.text = movies[index].name
                genre.text = movies[index].genre
                year.text = movies[index].year.toString()
                rating.text = movies[index].rating.toString()
                country.text = movies[index].country
                main_roles.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, movies[index].main_roles)
                producer.text = movies[index].producer
                seenMovies[index] = true
                break
            }
        }
    }

    fun onClearClick(view: View) {
        for (i in seenMovies.indices) {
            seenMovies[i] = false
        }
        findViewById<TextView>(R.id.title).text = ""
    }
}
