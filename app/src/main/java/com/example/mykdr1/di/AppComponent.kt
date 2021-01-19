package com.example.mykdr1.di

import com.example.mykdr1.KdrApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

// scope
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBindingModule::class
    ]
)
interface AppComponent : AndroidInjector<KdrApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: KdrApp): Builder

        fun build(): AppComponent
    }
}