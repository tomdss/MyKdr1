package com.example.mykdr1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.mykdr1.network.API_KEY
import com.example.mykdr1.network.IMAGE_BASE_URL
import com.example.mykdr1.network.MovieApi
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.collections.HashMap

class MovieActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var movieApi: MovieApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        initViews();
    }

    @SuppressLint("CheckResult")
    private fun initViews() {
        val tvTitleMovie: TextView = findViewById(R.id.tvMovieTitle)
        val ivPosterMovie: ImageView = findViewById(R.id.ivMoviePoster)
        val pbLoading: ProgressBar = findViewById(R.id.pbLoading)
        /*movieApi.getPopularMovie(API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    val movie = it.results[1]
                    Log.d("MovieActivity", movie.toString())
                    tvTitleMovie.text = movie.title
                    Picasso.get().load(IMAGE_BASE_URL + movie.posterPath).into(ivPosterMovie)
                    pbLoading.visibility = View.GONE
                },
                onError = {
                    Toast.makeText(this, "" + it.message, Toast.LENGTH_LONG).show()
                    pbLoading.visibility = View.GONE
                }
            )*/

        val data: HashMap<String, Any> = HashMap()
        data["api_key"] = API_KEY
        data["page"] = 1
        movieApi.getPopularMoviePage(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    val movie = it.results[1]
                    Log.d("MovieActivity", movie.toString())
                    tvTitleMovie.text = movie.title
                    Picasso.get().load(IMAGE_BASE_URL + movie.posterPath).into(ivPosterMovie)
                    pbLoading.visibility = View.GONE
                },
                onError = {
                    Toast.makeText(this, "" + it.message, Toast.LENGTH_LONG).show()
                    pbLoading.visibility = View.GONE
                }
            )

    }
}