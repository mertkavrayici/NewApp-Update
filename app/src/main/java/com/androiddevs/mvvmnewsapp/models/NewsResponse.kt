package com.androiddevs.mvvmnewsapp.models

import java.io.Serializable


data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
):Serializable