package com.dngwjy.newsapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dngwjy.newsapp.data.model.Result
import com.dngwjy.newsapp.databinding.ActivityMainBinding
import com.dngwjy.newsapp.databinding.ItemLayoutBinding
import com.dngwjy.newsapp.ui.common.DiffCallback
import com.dngwjy.newsapp.ui.common.GeneralRvViewBinding
import com.dngwjy.newsapp.ui.detail.DetailActivity
import com.dngwjy.newsapp.util.logD
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(),Observer<MainViewModel.MainState> {
    private lateinit var binding:ActivityMainBinding
    private val mainViewModel by viewModel<MainViewModel>()
    private val diffCallback=DiffCallback()

    private val articleAdapter by lazy{
        GeneralRvViewBinding<Result>(
            diffCallback =  diffCallback,
            inflate = { li, parent, attach ->
                ItemLayoutBinding.inflate(li,parent,attach)
            }, bind = {data, _, vb->
                with(vb as ItemLayoutBinding){
                    tvTitle.text=data.title
                    tvDatePost.text=data.published_date
                    if(data.media.isNotEmpty()) {
                        Glide.with(this.root).load(data.media[0].media_metadata[0].url)
                            .into(ivThumbnail)
                    }
                }
            },
            onClick = {data, _, vb->
                val intent=Intent(this, DetailActivity::class.java)
                intent.putExtra("data",data)
                startActivity(intent)
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel.uiState().observe(this,this)
        binding.rvArticles.apply {
            adapter=articleAdapter
            layoutManager=LinearLayoutManager(this@MainActivity)
        }
        if(savedInstanceState==null){
            mainViewModel.getArticles()
        }
        binding.srlRefresh.setOnRefreshListener {
            mainViewModel.getArticles()
        }
    }


    override fun onChanged(t: MainViewModel.MainState?) {
        when(t){
            is MainViewModel.MainState.ShowArticles->{
                logD("size data ${t.data.size}")
                binding.srlRefresh.isRefreshing=false
                articleAdapter.setData(t.data)
            }
            is MainViewModel.MainState.Loading->{
                binding.srlRefresh.isRefreshing=true
            }
            is MainViewModel.MainState.Error->{
                Toast.makeText(this,t.msg,Toast.LENGTH_LONG).show()
            }
            else -> {

            }
        }
    }
}