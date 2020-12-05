package fr.airweb.news.di

import dagger.Module
import dagger.Provides
import fr.airweb.news.BASE_URL
import fr.airweb.news.network.NewsService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module (includes = [ViewModelModule::class])
class ApplicationModule {

    @Singleton
    @Provides
    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideNewsService(retrofit: Retrofit) = retrofit.create(NewsService::class.java)

}