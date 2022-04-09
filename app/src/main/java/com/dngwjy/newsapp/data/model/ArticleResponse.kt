package com.dngwjy.newsapp.data.model

import android.os.Parcelable
import com.dngwjy.newsapp.data.local.ArticleLocal
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ArticleResponse(
    var copyright: String = "",
    var num_results: Int = 0,
    var results: List<Result> = listOf(),
    var status: String = ""
)
@Parcelize
data class Result(
    var `abstract`: String = "",
    var adx_keywords: String = "",
    var asset_id: Long = 0,
    var byline: String = "",
    var des_facet: List<String> = listOf(),
    var eta_id: Int = 0,
    var geo_facet: List<String> = listOf(),
    var id: Long = 0,
    var media: List<Media> = listOf(),
    var nytdsection: String = "",
    var org_facet: List<String> = listOf(),
    var per_facet: List<String> = listOf(),
    var published_date: String = "",
    var section: String = "",
    var source: String = "",
    var subsection: String = "",
    var title: String = "",
    var type: String = "",
    var updated: String = "",
    var uri: String = "",
    var url: String = ""
):Parcelable{
    fun toLocalData()=ArticleLocal(id,title,updated,published_date,`abstract`)
}

@Parcelize
data class Media(
    var approved_for_syndication: Int = 0,
    var caption: String = "",
    var copyright: String = "",
    @SerializedName("media-metadata")
    var media_metadata: List<MediaMetadata> = listOf(),
    var subtype: String = "",
    var type: String = ""
):Parcelable

@Parcelize
data class MediaMetadata(
    var format: String = "",
    var height: Int = 0,
    var url: String = "",
    var width: Int = 0
):Parcelable