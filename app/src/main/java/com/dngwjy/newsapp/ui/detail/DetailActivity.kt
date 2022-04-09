package com.dngwjy.newsapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dngwjy.newsapp.R
import com.dngwjy.newsapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}