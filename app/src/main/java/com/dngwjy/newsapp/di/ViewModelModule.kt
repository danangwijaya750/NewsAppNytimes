package com.dngwjy.newsapp.di

import com.dngwjy.newsapp.ui.main.MainViewModel
import org.koin.dsl.module

val viewModelModule= module {
    single {
        MainViewModel(get())
    }
}