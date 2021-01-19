package com.example.mykdr1.network

import android.content.Context
import android.widget.Toast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserService1 private constructor() {

    init {
    }

    private object Holder {
        val INSTANCE = UserService1()
    }

    companion object {
        fun getInstance(): UserService1 {
            return Holder.INSTANCE
        }
    }

    fun showToast(context: Context) {
        Toast.makeText(context, "text text", Toast.LENGTH_LONG).show()
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}