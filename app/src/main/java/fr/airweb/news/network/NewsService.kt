package fr.airweb.news.network

import fr.airweb.news.model.NewsModel
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET

interface NewsService {

    @GET("psg/psg.json")
    fun getNews(): Observable<NewsModel>
}