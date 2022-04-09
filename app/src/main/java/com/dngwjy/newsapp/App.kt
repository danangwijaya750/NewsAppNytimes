package com.dngwjy.newsapp

import android.app.Application
import com.dngwjy.newsapp.di.localStorageModule
import com.dngwjy.newsapp.di.networkModule
import com.dngwjy.newsapp.di.repositoryModule
import com.dngwjy.newsapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule, repositoryModule, viewModelModule, localStorageModule))
        }
    }
}