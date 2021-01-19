package com.example.mykdr1.di

import com.example.mykdr1.ImageLoader
import com.example.mykdr1.model.User
import com.example.mykdr1.network.UserService
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideUserApi() = UserService.create()

    @Provides
    fun provideLoadImage() = ImageLoader()

    @Provides
    @Singleton
    @Named("app-module")
    fun provideUser() = User()
}