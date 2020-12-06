package fr.airweb.news.ui

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import fr.airweb.news.base.BaseViewModel
import fr.airweb.news.model.NewsDao
import fr.airweb.news.model.NewsModel
import fr.airweb.news.network.NewsService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsListViewModel(val newsDao: NewsDao) : BaseViewModel() {
    @Inject
    lateinit var newsService: NewsService
    val adapter = NewsListAdapter()
    val disposable = CompositeDisposable()

    var newsList = MutableLiveData<List<NewsModel.NewsDetail>>()
    var error = MutableLiveData<String>()
    var loadingIsShown = MutableLiveData<Int>()

    val errorClickListener = View.OnClickListener { getAirwebNews() }

    init {
        getAirwebNews()
    }

    fun getAirwebNews() {
        disposable.add(
            Observable.fromCallable { newsDao.getAll }
                .concatMap { listNews ->
                    if(listNews.isEmpty()) {
                        newsService.getNews().concatMap { newsModel ->
                            newsDao.insertAll(newsModel.news)
                            Observable.just(newsModel.news)
                        }
                    }
                    else
                        Observable.just(listNews)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loadingIsShown.postValue(View.VISIBLE) }
                .map { newsDetail -> newsDetail.filter { it.type == "news" } }
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
        )

    }
}