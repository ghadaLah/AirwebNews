package fr.airweb.news.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import fr.airweb.news.R
import fr.airweb.news.base.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: NewsListViewModel by lazy { ViewModelProviders.of(this, viewModelFactory)[NewsListViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        viewModel = ViewModelProviders.of(this, viewModelFactory)[NewsListViewModel::class.java]
        buildListeners()
    }

    override fun onResume() {
        super.onResume()

    }

    fun buildListeners() {
        viewModel.newsList.observe(this, Observer {
            Log.d("airwebNews", "irwebNews got ok activity result $it")
        })

        viewModel.error.observe(this, Observer {
            Log.d("airwebNews", "irwebNews got ko activity throwable $it")
        })
    }
}