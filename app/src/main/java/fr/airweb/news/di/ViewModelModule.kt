package fr.airweb.news.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import fr.airweb.news.base.ViewModelFactory
import fr.airweb.news.ui.NewsListViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    abstract fun provideNewsListViewModel(newsListViewModel: NewsListViewModel): ViewModel

    @Binds
    abstract fun providesViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}