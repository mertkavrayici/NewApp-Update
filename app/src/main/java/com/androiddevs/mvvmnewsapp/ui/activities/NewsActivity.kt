package com.androiddevs.mvvmnewsapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.ui.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_news.*

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    private val toggle by lazy {
        ActionBarDrawerToggle(this, drawer_layout, R.string.open, R.string.close)
    }
    val viewModel: NewsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.country1 -> {
                    val intent = Intent(this, NewsActivity::class.java)
                    startActivity(intent)
                }
                R.id.country2 -> {
                    val intent = Intent(this, UsNewsActivity::class.java)
                    startActivity(intent)

                }
                R.id.country3 -> {
                    val intent = Intent(this, DeNewsActivity::class.java)
                    startActivity(intent)

                }

            }
            true

        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

}
