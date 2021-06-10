package com.androiddevs.mvvmnewsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.adapters.NewsAdapter
import com.androiddevs.mvvmnewsapp.ui.activities.NewsActivity
import com.androiddevs.mvvmnewsapp.ui.NewsViewModel
import com.androiddevs.mvvmnewsapp.ui.activities.DeNewsActivity
import com.androiddevs.mvvmnewsapp.ui.activities.UsNewsActivity
import com.androiddevs.mvvmnewsapp.util.Constants.Companion.QUERY_PAGE_SIZE
import com.androiddevs.mvvmnewsapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_breaking_news.*
@AndroidEntryPoint
class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    lateinit var viewModel: NewsViewModel

    lateinit var newsAdapter: NewsAdapter

    val TAG = "BreakingNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

viewModel=activity.let {
    when(it){

        is NewsActivity ->it.viewModel
        is UsNewsActivity ->it.viewModel

        is DeNewsActivity -> it.viewModel

        else -> error("")
    }

}




        setupRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_breakingNewsFragment_to_articleFragment,
                bundle
            )
        }

        if(activity!! is NewsActivity){
            viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
                when(response) {
                    is Resource.Success -> {
                        hideProgressBar()
                        response.data?.let { newsResponse ->
                            newsAdapter.differ.submitList(newsResponse.articles.toList())
                            val totalPages=newsResponse.totalResults/ QUERY_PAGE_SIZE+2 //Sayfada boşluk oluşuyor
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
        else if(activity!! is DeNewsActivity){
            viewModel.deBreakingNews.observe(viewLifecycleOwner, Observer { response ->
                when(response) {
                    is Resource.Success -> {
                        hideProgressBar()
                        response.data?.let { newsResponse ->
                            newsAdapter.differ.submitList(newsResponse.articles.toList())
                            val totalPages=newsResponse.totalResults/ QUERY_PAGE_SIZE+2 //Sayfada boşluk oluşuyor
                            isLastPage=viewModel.deBreakingNewsPage==totalPages
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

            viewModel.usBreakingNews.observe(viewLifecycleOwner, Observer { response ->
                when(response) {
                    is Resource.Success -> {
                        hideProgressBar()
                        response.data?.let { newsResponse ->
                            newsAdapter.differ.submitList(newsResponse.articles.toList())
                            val totalPages=newsResponse.totalResults/ QUERY_PAGE_SIZE+2 //Sayfada boşluk oluşuyor
                            isLastPage=viewModel.usBreakingNewsPage==totalPages
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
        paginationProgressBar.visibility = View.INVISIBLE
        isLoading=false
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE

        isLoading=true
    }



    var isLoading=false
    var isLastPage=false
    var isScrooling=false

    val scrollListener=object :RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (newState==AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrooling=true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager=recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition=layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount=layoutManager.childCount
            val totalItemCount=layoutManager.itemCount

            val isNotLoadingAndNotLastPage=!isLoading && !isLastPage

            val isAtLastItem=firstVisibleItemPosition+visibleItemCount >= totalItemCount

            val isNotBegining=firstVisibleItemPosition >= 0

            val isTotalMoreThanVisible=totalItemCount>=QUERY_PAGE_SIZE

            val shouldPaginate=isNotLoadingAndNotLastPage && isAtLastItem && isNotBegining && isTotalMoreThanVisible && isScrooling

            if(shouldPaginate){

                if(activity!! is NewsActivity){


                    viewModel.getBreakingNews("tr")
                    isScrooling = false

                }
                else if(activity!! is UsNewsActivity){


                    viewModel.getUsBreakingNews("us")
                    isScrooling = false

                }
                else if(activity!! is DeNewsActivity){


                    viewModel.getDeBreakingNews("de")
                    isScrooling = false

                }



            }




            }
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(this.context,DividerItemDecoration.VERTICAL))
            addOnScrollListener(this@BreakingNewsFragment.scrollListener)
        }
    }
}