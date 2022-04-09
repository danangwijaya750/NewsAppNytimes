package com.dngwjy.newsapp.di

import com.dngwjy.newsapp.data.repository.ArticleRepositoryImpl
import org.koin.dsl.module

val repositoryModule=module{
    single {
        ArticleRepositoryImpl(get(),get(),get())
    }
}