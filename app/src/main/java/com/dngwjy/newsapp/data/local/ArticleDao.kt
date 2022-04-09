package com.dngwjy.newsapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticleDao {
    @Query("SELECT * FROM ArticleLocal")
    fun findAll(): List<ArticleLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(data: List<ArticleLocal>)
}