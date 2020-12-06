package fr.airweb.news.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.airweb.news.R
import fr.airweb.news.base.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val viewModel: NewsListViewModel by lazy { ViewModelProviders.of(this, ViewModelFactory(this))[NewsListViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        viewModel = ViewModelProviders.of(this, viewModelFactory)[NewsListViewModel::class.java]
        collapsingToolbar.isTitleEnabled = false
        toolbar.title = "Airweb News"

        buildListeners()
        setUpadapter()
    }

    fun setUpadapter() {
        val layoutmanager = LinearLayoutManager(this)
        layoutmanager.orientation = LinearLayoutManager.VERTICAL
        newsList.layoutManager = layoutmanager
        newsList.adapter = viewModel.adapter
    }

    fun buildListeners() {
        viewModel.newsList.observe(this, Observer {
            viewModel.adapter.addAllNews(it.news)
        })

        viewModel.error.observe(this, Observer {
            Log.d("airwebNews", "irwebNews got ko activity throwable $it")
        })
    }
}