package com.androiddevs.mvvmnewsapp.api

import com.androiddevs.mvvmnewsapp.models.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService:NewsAPI): ApiHelper {
    override suspend fun getBreakingNews(): Response<NewsResponse> =apiService.getBreakingNews("tr")

    override suspend fun getUsBreakingNews(): Response<NewsResponse> =apiService.getBreakingNews("us")

    override suspend fun getDeBreakingNews(): Response<NewsResponse> =apiService.getBreakingNews("de")

    override suspend fun searchForNews(): Response<NewsResponse> =apiService.searchForNews("")

    override suspend fun getSportsNews(): Response<NewsResponse> =apiService.getCategoryNews("sports","tr")

    override suspend fun getDeSportsNews(): Response<NewsResponse> =apiService.getCategoryNews("sports","de")

    override suspend fun getUsSportsNews(): Response<NewsResponse> = apiService.getCategoryNews("sports","us")

    override suspend fun getTechNews(): Response<NewsResponse> = apiService.getCategoryNews("technology","tr")

    override suspend fun getDeTechNews(): Response<NewsResponse> = apiService.getCategoryNews("technology","de")

    override suspend fun getUsTechNews(): Response<NewsResponse> =apiService.getCategoryNews("technology","us")
}