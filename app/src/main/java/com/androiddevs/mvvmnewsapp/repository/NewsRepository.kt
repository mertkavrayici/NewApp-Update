package com.androiddevs.mvvmnewsapp.repository


import com.androiddevs.mvvmnewsapp.api.ApiHelper
import com.androiddevs.mvvmnewsapp.db.ArticleDatabase
import com.androiddevs.mvvmnewsapp.models.Article
import javax.inject.Inject


class NewsRepository @Inject constructor(
    val db: ArticleDatabase, private val apiHelper: ApiHelper
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        apiHelper.getBreakingNews()

    suspend fun getUsBreakingNews(countryCode: String, pageNumber: Int) =
        apiHelper.getUsBreakingNews()

    suspend fun getDeBreakingNews(countryCode: String, pageNumber: Int) =
        apiHelper.getDeBreakingNews()

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()
    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        apiHelper.searchForNews()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)


    suspend fun getSportsNews(category: String, countryCode: String, pageNumber: Int) =
        apiHelper.getSportsNews()


    suspend fun getTechNews(category: String, countryCode: String, pageNumber: Int) =
        apiHelper.getTechNews()


    suspend fun getUsSportsNews(category: String, countryCode: String, pageNumber: Int) =
        apiHelper.getUsSportsNews()


    suspend fun getDeTechNews(category: String, countryCode: String, pageNumber: Int) =
        apiHelper.getDeTechNews()

    suspend fun getDeSportsNews(category: String, countryCode: String, pageNumber: Int) =
        apiHelper.getDeSportsNews()

    suspend fun getUsTechNews(category: String, countryCode: String, pageNumber: Int) =
        apiHelper.getUsTechNews()


}