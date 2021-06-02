package com.androiddevs.mvvmnewsapp.models


import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity( tableName = "articles", indices = [Index(value = ["description"],unique = true)] )
data class  Article(
    @PrimaryKey(autoGenerate = true)
    var Id: Int? = null,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
) : Serializable