package fr.airweb.news.ui

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import fr.airweb.news.base.BaseViewModel
import fr.airweb.news.model.NewsDao
import fr.airweb.news.model.NewsModel
import fr.airweb.news.network.NewsService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsListViewModel(val newsDao: NewsDao) : BaseViewModel() {
    @Inject
    lateinit var newsService: NewsService
    val adapter = NewsListAdapter()

    var newsList = MutableLiveData<NewsModel>()
    var error = MutableLiveData<String>()
    var loadingIsShown = MutableLiveData<Int>()

    val errorClickListener = View.OnClickListener { getAirwebNews() }

    init {
        getAirwebNews()
    }

    fun getAirwebNews() {
        newsService.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loadingIsShown.postValue(View.VISIBLE) }
            .subscribe(
                {
                    newsList.postValue(it)
                    loadingIsShown.postValue(View.GONE)
                },
                {
                    error.postValue("Error - getNews API : $it")
                    loadingIsShown.postValue(View.GONE)
                }
            )
    }
}