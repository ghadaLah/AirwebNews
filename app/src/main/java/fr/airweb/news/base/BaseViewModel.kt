package fr.airweb.news.base

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import fr.airweb.news.di.ApplicationModule
import fr.airweb.news.di.DaggerAppComponent
import fr.airweb.news.ui.NewsListViewModel

abstract class BaseViewModel: ViewModel() {
    val injector = DaggerAppComponent.builder()
        .application(ApplicationModule)
        .Build()

    init {
        inject()
    }

    fun inject() {
        when(this) {
            is NewsListViewModel -> injector.inject(this)
            is AndroidViewModel -> injector.inject(this)
        }
    }
}