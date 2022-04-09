package com.dngwjy.newsapp.data.repository

import com.dngwjy.newsapp.data.model.Result
import com.dngwjy.newsapp.util.AppResult

interface ArticleRepository {
    suspend fun getArticle():AppResult<List<Result>>
}