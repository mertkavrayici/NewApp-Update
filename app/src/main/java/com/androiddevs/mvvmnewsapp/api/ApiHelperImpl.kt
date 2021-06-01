package com.androiddevs.mvvmnewsapp.api

import com.androiddevs.mvvmnewsapp.models.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService:NewsAPI): ApiHelper {
    override suspend fun getBreakingNews(): Response<NewsResponse> =apiService.getBreakingNews()

    override suspend fun getUsBreakingNews(): Response<NewsResponse> =apiService.getUsBreakingNews()

    override suspend fun getDeBreakingNews(): Response<NewsResponse> =apiService.getDeBreakingNews()

    override suspend fun searchForNews(): Response<NewsResponse> =apiService.searchForNews("q")

    override suspend fun getSportsNews(): Response<NewsResponse> =apiService.getSportsNews()

    override suspend fun getDeSportsNews(): Response<NewsResponse> =apiService.getDeSportsNews()

    override suspend fun getUsSportsNews(): Response<NewsResponse> = apiService.getUsSportsNews()

    override suspend fun getTechNews(): Response<NewsResponse> = apiService.getTechNews()

    override suspend fun getDeTechNews(): Response<NewsResponse> = apiService.getDeTechNews()

    override suspend fun getUsTechNews(): Response<NewsResponse> =apiService.getUsTechNews()
}