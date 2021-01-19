package com.example.mykdr1.di

import com.example.mykdr1.MainActivity
import com.example.mykdr1.MovieActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [UserModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindMovieActivity(): MovieActivity
}