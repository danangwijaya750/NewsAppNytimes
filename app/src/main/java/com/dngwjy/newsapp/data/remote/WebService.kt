package com.dngwjy.newsapp.data.remote

import com.dngwjy.newsapp.data.model.ArticleResponse
import com.dngwjy.newsapp.di.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("svc/mostpopular/v2/viewed/1.json")
    suspend fun getPopularArticle(@Query("api-key")api_key:String):ArticleResponse
}