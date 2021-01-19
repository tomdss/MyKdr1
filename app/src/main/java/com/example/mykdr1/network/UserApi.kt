package com.example.mykdr1.network

import com.example.mykdr1.model.UserResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface UserApi {
    @GET("users/tomdss")
    fun getUser(): Call<UserResponse>

    @GET("users/tomdss")
    fun getUserWithRx(): Single<UserResponse>
}