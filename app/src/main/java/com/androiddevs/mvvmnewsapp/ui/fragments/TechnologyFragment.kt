package com.androiddevs.mvvmnewsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AbsListView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.adapters.NewsAdapter
import com.androiddevs.mvvmnewsapp.ui.activities.NewsActivity
import com.androiddevs.mvvmnewsapp.ui.NewsViewModel
import com.androiddevs.mvvmnewsapp.util.Constants
import com.androiddevs.mvvmnewsapp.util.Resource
import kotlinx.android.synthetic.main.fragment_entertainment.*
import kotlinx.android.synthetic.main.fragment_entertainment.paginationProgressBar2
import kotlinx.android.synthetic.main.fragment_sports.*
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.androiddevs.mvvmnewsapp.ui.activities.DeNewsActivity
import com.androiddevs.mvvmnewsapp.ui.activities.UsNewsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_breaking_news.*
import kotlinx.android.synthetic.main.fragment_technology.*
@AndroidEntryPoint
class TechnologyFragment :Fragment(R.layout.fragment_technology) {
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    val TAG = "TechNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=activity.let {
            when(it){

                is NewsActivity ->it.viewModel
                is UsNewsActivity ->it.viewModel
                is DeNewsActivity -> it.viewModel
                else -> error("Lütfen Yanlış Yapma")
            }
        }

        setupRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_technologyFragment_to_articleFragment,
                bundle
            )

        }


        if(activity!! is NewsActivity){
            viewModel.techNews.observe(viewLifecycleOwner, Observer { response ->
                when(response) {
                    is Resource.Success -> {
                        hideProgressBar()
                        response.data?.let { newsResponse ->
                            newsAdapter.differ.submitList(newsResponse.articles.toList())
                            val totalPages=newsResponse.totalResults/ Constants.QUERY_PAGE_SIZE +2 //Sayfada boşluk oluşuyor
                            isLastPage=viewModel.breakingNewsPage==totalPages
                            if(isLastPage){
                                rvBreakingNews.setPadding(0,0,0,0)
                            }
                        }
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        response.message?.let { message ->
                            Log.e(TAG, "An error occured: $message")
                        }
                    }
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                }
            })
        }
        if(activity!! is DeNewsActivity){
            viewModel.deTechNews.observe(viewLifecycleOwner, Observer { response ->
                when(response) {
                    is Resource.Success -> {
                        hideProgressBar()
                        response.data?.let { newsResponse ->
                            newsAdapter.differ.submitList(newsResponse.articles.toList())
                            val totalPages=newsResponse.totalResults/ Constants.QUERY_PAGE_SIZE +2 //Sayfada boşluk oluşuyor
                            isLastPage=viewModel.deTechNewsPage==totalPages
                            if(isLastPage){
                                rvBreakingNews.setPadding(0,0,0,0)
                            }
                        }
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        response.message?.let { message ->
                            Log.e(TAG, "An error occured: $message")
                        }
                    }
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                }
            })
        }
        else if (activity is UsNewsActivity){

            viewModel.usTechNews.observe(viewLifecycleOwner, Observer { response ->
                when(response) {
                    is Resource.Success -> {
                        hideProgressBar()
                        response.data?.let { newsResponse ->
                            newsAdapter.differ.submitList(newsResponse.articles.toList())
                            val totalPages=newsResponse.totalResults/ Constants.QUERY_PAGE_SIZE +2 //Sayfada boşluk oluşuyor
                            isLastPage=viewModel.breakingNewsPage==totalPages
                            if(isLastPage){
                                rvBreakingNews.setPadding(0,0,0,0)
                            }
                        }
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        response.message?.let { message ->
                            Log.e(TAG, "An error occured: $message")
                        }
                    }
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                }
            })
        }

    }

    private fun hideProgressBar() {
        paginationProgressBar2.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        paginationProgressBar2.visibility = View.VISIBLE

        isLoading = true
    }

    var isLoading = false
    var isLastPage = false
    var isScrooling = false

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrooling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage

            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount

            val isNotBegining = firstVisibleItemPosition >= 0

            val isTotalMoreThanVisible = totalItemCount >= Constants.QUERY_PAGE_SIZE

            val shouldPaginate =
                isNotLoadingAndNotLastPage && isAtLastItem && isNotBegining && isTotalMoreThanVisible && isScrooling

            if (shouldPaginate) {

                viewModel.getTechNews("technology","tr")
                isScrooling = false

            }
        }


    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvTechNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            addOnScrollListener(this@TechnologyFragment.scrollListener)
        }
    }
}