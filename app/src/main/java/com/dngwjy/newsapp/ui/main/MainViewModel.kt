package com.dngwjy.newsapp.ui.main

import androidx.lifecycle.viewModelScope
import com.dngwjy.newsapp.base.BaseViewModel
import com.dngwjy.newsapp.data.model.Result
import com.dngwjy.newsapp.data.repository.ArticleRepositoryImpl
import com.dngwjy.newsapp.util.AppResult
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ArticleRepositoryImpl): BaseViewModel<MainViewModel.MainState>() {
    sealed class MainState{
        object Loading:MainState()
        data class Error(val msg:String):MainState()
        data class ShowArticles(val data:List<Result>):MainState()
    }
    fun getArticles(){
        uiState.value=MainState.Loading
        viewModelScope.launch {
            when(val res=repository.getArticle()){
                is AppResult.Success->{
                    uiState.value=MainState.ShowArticles(res.successData)
                }
                is AppResult.Error->{
                    uiState.value=MainState.Error(res.message)
                }
            }
        }
    }


}