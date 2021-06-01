package com.androiddevs.mvvmnewsapp.api

import com.androiddevs.mvvmnewsapp.models.NewsResponse
import com.androiddevs.mvvmnewsapp.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "tr",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>
    @GET("v2/top-headlines")
    suspend fun getUsBreakingNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>
    @GET("v2/top-headlines")
    suspend fun getDeBreakingNews(
        @Query("country")
        countryCode: String = "de",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

    @GET("/v2/top-headlines")
    suspend fun getSportsNews(
        @Query("category")
        category: String="sports",
        @Query("country")
        countryCode: String="tr",
        @Query("page")
        pageNumber: Int=1,
        @Query("apiKey")
        apiKey: String = API_KEY

    ): Response<NewsResponse>
    @GET("/v2/top-headlines")
    suspend fun getDeSportsNews(
        @Query("category")
        category: String="sports",
        @Query("country")
        countryCode: String="de",
        @Query("page")
        pageNumber: Int=1,
        @Query("apiKey")
        apiKey: String = API_KEY

    ): Response<NewsResponse>
    @GET("/v2/top-headlines")
    suspend fun getUsSportsNews(
        @Query("category")
        category: String="sports",
        @Query("country")
        countryCode: String="us",
        @Query("page")
        pageNumber: Int=1,
        @Query("apiKey")
        apiKey: String = API_KEY

    ): Response<NewsResponse>
    @GET("/v2/top-headlines")
    suspend fun getTechNews(
        @Query("category")
        category: String="technology",
        @Query("country")
        countryCode: String="tr",
        @Query("page")
        pageNumber: Int=1,
        @Query("apiKey")
        apiKey: String = API_KEY

    ): Response<NewsResponse>
    @GET("/v2/top-headlines")
    suspend fun getDeTechNews(
        @Query("category")
        category: String="technology",
        @Query("country")
        countryCode: String="de",
        @Query("page")
        pageNumber: Int=1,
        @Query("apiKey")
        apiKey: String = API_KEY

    ): Response<NewsResponse>
    @GET("/v2/top-headlines")
    suspend fun getUsTechNews(
        @Query("category")
        category: String="technology",
        @Query("country")
        countryCode: String="us",
        @Query("page")
        pageNumber: Int=1,
        @Query("apiKey")
        apiKey: String = API_KEY

    ): Response<NewsResponse>

}