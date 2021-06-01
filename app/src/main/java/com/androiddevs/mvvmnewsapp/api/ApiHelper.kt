package com.androiddevs.mvvmnewsapp.api

import com.androiddevs.mvvmnewsapp.models.NewsResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun getBreakingNews():Response<NewsResponse>
    suspend fun getUsBreakingNews():Response<NewsResponse>
    suspend fun getDeBreakingNews():Response<NewsResponse>
    suspend fun searchForNews():Response<NewsResponse>
    suspend fun getSportsNews():Response<NewsResponse>
    suspend fun getDeSportsNews():Response<NewsResponse>
    suspend fun getUsSportsNews():Response<NewsResponse>
    suspend fun getTechNews():Response<NewsResponse>
    suspend fun getDeTechNews():Response<NewsResponse>
    suspend fun getUsTechNews():Response<NewsResponse>
}