package com.dngwjy.newsapp.di

import android.app.Application
import androidx.room.Room
import com.dngwjy.newsapp.data.local.ArticleDao
import com.dngwjy.newsapp.data.local.DatabaseInstance
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localStorageModule= module {
    fun provideDatabase(application: Application): DatabaseInstance {
        return Room.databaseBuilder(application, DatabaseInstance::class.java, "ArticleLocal")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCountriesDao(database: DatabaseInstance): ArticleDao {
        return  database.articleDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }
}