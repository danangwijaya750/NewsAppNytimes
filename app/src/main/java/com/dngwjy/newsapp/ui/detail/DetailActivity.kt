package com.dngwjy.newsapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dngwjy.newsapp.R
import com.dngwjy.newsapp.data.model.Result
import com.dngwjy.newsapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = intent.extras?.get("data") as Result
        setData(data)
    }
    private fun setData(data:Result?){
        if(data!=null){
            with(binding){
                tvTitle.text=data.title
                tvAbstract.text=data.abstract
                tvDatePost.text="Published :${data.published_date}"
                tvDateUpdated.text="Updated :${data.updated}"
                if(data.media.isNotEmpty()){
                    Glide.with(this.root).load(data.media[0].media_metadata[2].url).into(ivThumbnail)
                }
            }
        }
    }
}