package com.dngwjy.newsapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dngwjy.newsapp.data.model.Result

@Entity
data class ArticleLocal (
    @PrimaryKey var id: Long = 0,
    var title:String ="",
    var updated: String = "",
    var `abstract`: String = "",
    ){
    fun toResult()=Result(abstract= `abstract`, id=id, title = title, updated = updated)
}