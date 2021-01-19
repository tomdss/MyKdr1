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
import com.example.mykdr1.model.User
import com.example.mykdr1.model.UserResponse
import com.example.mykdr1.network.UserApi
import com.example.mykdr1.network.UserService
import com.example.mykdr1.network.UserService1
import com.google.gson.Gson
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class MainActivity : DaggerAppCompatActivity() {
    @Inject // anotation
    lateinit var userApi: UserApi

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    @Named("app-module")
    lateinit var user1: User

    @Inject
    @Named("app-module")
    lateinit var user11: User

    @Inject
    @Named("user-module")
    lateinit var user2: User

    private var disposable: Disposable? = null

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
//        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        initViews()

        val tvText: TextView = findViewById(R.id.tvText)
        val ivAvatar: ImageView = findViewById(R.id.ivAvatar)
        val pbLoading: ProgressBar = findViewById(R.id.pbLoading)

        /*userApi.getUser()
            .enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    tvText.text = "onFailure : ${t.message}"
                }

                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    tvText.text = "onResponse : ${response.body()?.type}"
                }

            })*/

        tvText.text = "onScopeUser :\n 1 = $user1 \n 2 = $user2 \n 11 = $user11"


        userApi.getUserWithRx()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                disposable = it
            }
            .filter { it.publicRepos > 30 }
            .subscribeBy(
                onSuccess = {
                    imageLoader.loadImage(it.avatarUrl, ivAvatar)
                    pbLoading.visibility = View.GONE
                },
                onError = {
                    tvText.text = "onFailure : ${it.message}"
                }
            )
    }

    private fun initViews() {
        UserService.create().getUser().enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
                Log.d(TAG, Gson().toJson(t.message))
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                Log.d(TAG, Gson().toJson(response.body()))
            }
        })
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }

    companion object {
        val TAG: String = "MainActivity"
    }
}