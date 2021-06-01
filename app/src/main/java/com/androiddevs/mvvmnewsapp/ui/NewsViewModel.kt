package com.androiddevs.mvvmnewsapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.mvvmnewsapp.models.Article
import com.androiddevs.mvvmnewsapp.models.NewsResponse
import com.androiddevs.mvvmnewsapp.repository.NewsRepository
import com.androiddevs.mvvmnewsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel @ViewModelInject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1
    var breakingNewsResponse:NewsResponse?=null
    val usBreakingNews:MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var usBreakingNewsPage = 1
    var usBreakingNewsResponse:NewsResponse?=null

    val deBreakingNews:MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var deBreakingNewsPage = 1
    var deBreakingNewsResponse:NewsResponse?=null

    val deSportsNews:MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var deSportsNewsPage = 1
    var deSportsNewsResponse:NewsResponse?=null

    val deTechNews:MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var deTechNewsPage = 1
    var deTechNewsResponse:NewsResponse?=null



    val usSportsNews:MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var usSportsNewsPage = 1
    var usSportsNewsResponse:NewsResponse?=null
    val usTechNews:MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var usTechNewsPage = 1
    var usTechNewsResponse:NewsResponse?=null

    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1

    val sportsNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var sportsNewsPage=1
    var sportsNewsResponse:NewsResponse?=null

    val techNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var techNewsPage=1
    var techNewsResponse:NewsResponse?=null







    init {
        getBreakingNews("tr")
        getUsBreakingNews("us")
        getDeBreakingNews("de")
        getTechNews("technology","tr")
        getSportsNews("sports","tr")
        getUsTechNews("technology","us")
        getUsSportsNews("sports","us")
        getDeTechNews("technology","de")
        getDeSportsNews("sports","de")





    }

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }
    fun getUsBreakingNews(countryCode: String) = viewModelScope.launch {
        usBreakingNews.postValue(Resource.Loading())
        val response = newsRepository.getUsBreakingNews(countryCode, usBreakingNewsPage)
        usBreakingNews.postValue(handleUsBreakingNewsResponse(response))
    }
    fun getUsTechNews(category: String,countryCode: String) = viewModelScope.launch {
        usTechNews.postValue(Resource.Loading())
        val response = newsRepository.getUsTechNews(category,countryCode, usTechNewsPage)
        usTechNews.postValue(handleUsTechNewsResponse(response))
    }
    fun getUsSportsNews(category: String,countryCode: String) = viewModelScope.launch {
        usSportsNews.postValue(Resource.Loading())
        val response = newsRepository.getUsSportsNews(category,countryCode, usSportsNewsPage)
        usSportsNews.postValue(handleUsSportsNewsResponse(response))
    }
    fun getDeBreakingNews(countryCode: String) = viewModelScope.launch {
        deBreakingNews.postValue(Resource.Loading())
        val response = newsRepository.getDeBreakingNews(countryCode, deBreakingNewsPage)
        deBreakingNews.postValue(handleDeBreakingNewsResponse(response))
    }
    fun getDeTechNews(category: String,countryCode: String) = viewModelScope.launch {
        deTechNews.postValue(Resource.Loading())
        val response = newsRepository.getDeTechNews(category,countryCode, deTechNewsPage)
        deTechNews.postValue(handleDeTechNewsResponse(response))
    }
    fun getDeSportsNews(category: String,countryCode: String) = viewModelScope.launch {
        deSportsNews.postValue(Resource.Loading())
        val response = newsRepository.getDeSportsNews(category,countryCode, deSportsNewsPage)
        deSportsNews.postValue(handleDeSportsNewsResponse(response))
    }



    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews(searchQuery, searchNewsPage)
        searchNews.postValue(handleSearchNewsResponse(response))
    }

    fun getSportsNews(category: String,countryCode: String)=viewModelScope.launch {
        sportsNews.postValue(Resource.Loading())
        val response=newsRepository.getSportsNews(category,countryCode,sportsNewsPage)
        sportsNews.postValue(handleSportsNewsResponse(response))
    }
    fun getTechNews(category: String,countryCode: String)=viewModelScope.launch {
        techNews.postValue(Resource.Loading())
        val response=newsRepository.getTechNews(category,countryCode,techNewsPage)
        techNews.postValue(handleTechNewsResponse(response))
    }


    private fun handleBreakingNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                breakingNewsPage++

                if(breakingNewsResponse==null){
                    breakingNewsResponse=resultResponse
                }
                else{
                    val oldArticles=breakingNewsResponse?.articles
                    val newArticles=resultResponse.articles
                    oldArticles?.addAll(newArticles)

                }
                return Resource.Success(breakingNewsResponse?:resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
    private fun handleUsBreakingNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                usBreakingNewsPage++

                if(usBreakingNewsResponse==null){
                    usBreakingNewsResponse=resultResponse
                }
                else{
                    val oldArticles=usBreakingNewsResponse?.articles
                    val newArticles=resultResponse.articles
                    oldArticles?.addAll(newArticles)

                }
                return Resource.Success(usBreakingNewsResponse?:resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
    private fun handleUsSportsNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                usSportsNewsPage++

                if(usSportsNewsResponse==null){
                    usSportsNewsResponse=resultResponse
                }
                else{
                    val oldArticles=usSportsNewsResponse?.articles
                    val newArticles=resultResponse.articles
                    oldArticles?.addAll(newArticles)

                }
                return Resource.Success(usSportsNewsResponse?:resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
    private fun handleUsTechNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                usTechNewsPage++

                if(usTechNewsResponse==null){
                    usTechNewsResponse=resultResponse
                }
                else{
                    val oldArticles=usTechNewsResponse?.articles
                    val newArticles=resultResponse.articles
                    oldArticles?.addAll(newArticles)

                }
                return Resource.Success(usTechNewsResponse?:resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
    private fun handleDeBreakingNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                deBreakingNewsPage++

                if(deBreakingNewsResponse==null){
                    deBreakingNewsResponse=resultResponse
                }
                else{
                    val oldArticles=deBreakingNewsResponse?.articles
                    val newArticles=resultResponse.articles
                    oldArticles?.addAll(newArticles)

                }
                return Resource.Success(deBreakingNewsResponse?:resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
    private fun handleDeSportsNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                deSportsNewsPage++

                if(deSportsNewsResponse==null){
                    deSportsNewsResponse=resultResponse
                }
                else{
                    val oldArticles=deSportsNewsResponse?.articles
                    val newArticles=resultResponse.articles
                    oldArticles?.addAll(newArticles)

                }
                return Resource.Success(deSportsNewsResponse?:resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
    private fun handleDeTechNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                deTechNewsPage++

                if(deTechNewsResponse==null){
                    deTechNewsResponse=resultResponse
                }
                else{
                    val oldArticles=deTechNewsResponse?.articles
                    val newArticles=resultResponse.articles
                    oldArticles?.addAll(newArticles)

                }
                return Resource.Success(deTechNewsResponse?:resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
    private fun handleSportsNewsResponse(response: Response<NewsResponse>):Resource<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse->
                sportsNewsPage++

                if (sportsNewsResponse==null){
                    sportsNewsResponse=resultResponse
                }
                else{
                    val oldArticles=sportsNewsResponse?.articles
                    val newArticles=resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return  Resource.Success(sportsNewsResponse?:resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
    private fun handleTechNewsResponse(response: Response<NewsResponse>):Resource<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse->
                techNewsPage++

                if (techNewsResponse==null){
                    techNewsResponse=resultResponse
                }
                else{
                    val oldArticles=techNewsResponse?.articles
                    val newArticles=resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return  Resource.Success(techNewsResponse?:resultResponse)
            }
        }
        return Resource.Error(response.message())
    }



    private fun handleSearchNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {

            if(response.isSuccessful) {
                response.body()?.let { resultResponse ->
                    return Resource.Success(resultResponse)

                }
            }
        return Resource.Error(response.message())
        }



    fun saveArticle(article: Article)=viewModelScope.launch {
        newsRepository.upsert(article)
    }
    fun getSavedNews()=newsRepository.getSavedNews()

    fun deleteArticle(article: Article) =viewModelScope.launch {

        newsRepository.deleteArticle(article)
    }
}












