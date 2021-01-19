package com.example.mykdr1.di

import com.example.mykdr1.model.User
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class UserModule {
    @Provides
    @Named("user-module")
    fun provideUser() = User()
}