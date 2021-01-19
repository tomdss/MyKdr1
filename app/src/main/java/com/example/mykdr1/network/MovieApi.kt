package com.example.mykdr1.network

import com.example.mykdr1.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.*

interface MovieApi {
    @GET("movie/popular")
    fun getPopularMovie(@Query("api_key") apiKey: String): Single<MovieResponse>

    @GET("movie/popular")
    fun getPopularMoviePage(@QueryMap data: HashMap<String, Any>): Single<MovieResponse>

}