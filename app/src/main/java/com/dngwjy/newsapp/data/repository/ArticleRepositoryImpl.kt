package com.dngwjy.newsapp.data.repository

import android.content.Context
import com.dngwjy.newsapp.data.local.ArticleDao
import com.dngwjy.newsapp.data.local.ArticleLocal
import com.dngwjy.newsapp.data.model.Result
import com.dngwjy.newsapp.data.remote.WebService
import com.dngwjy.newsapp.di.Constants
import com.dngwjy.newsapp.util.AppResult
import com.dngwjy.newsapp.util.NetworkUtil.isOnline
import com.dngwjy.newsapp.util.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArticleRepositoryImpl(private val service: WebService, private val context: Context, private val articleDao: ArticleDao)
    : ArticleRepository {

    override suspend fun getArticle(): AppResult<List<Result>> {
        return if(isOnline(context)){
            try{
                val res = service.getPopularArticle(Constants.API_KEY)
                val data = res.results.map { it.toLocalData() }
                data.let { local->
                    withContext(Dispatchers.IO){articleDao.add(local)}
                }
                AppResult.Success(res.results)
            }catch (e:Exception){
                AppResult.Error(e)
            }
        }else{
            val data=getDataFromLocal().map { it.toResult() }
            context.toast("Koneksi Internet Tidak Ada")
            AppResult.Success(data)
        }
    }

    private suspend fun getDataFromLocal():List<ArticleLocal>{
       return withContext(Dispatchers.IO){
           articleDao.findAll()
       }
    }


}