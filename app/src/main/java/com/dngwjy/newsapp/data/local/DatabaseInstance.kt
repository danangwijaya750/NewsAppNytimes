package com.dngwjy.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import java.lang.annotation.Native
import java.util.jar.Attributes

@Database(
    entities = [ArticleLocal::class],
    version = 1, exportSchema = false
)
abstract class DatabaseInstance :RoomDatabase(){
    abstract val articleDao:ArticleDao
}