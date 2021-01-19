package com.example.mykdr1.di

import com.example.mykdr1.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [UserModule::class])
    abstract fun bindMainActivity(): MainActivity
}