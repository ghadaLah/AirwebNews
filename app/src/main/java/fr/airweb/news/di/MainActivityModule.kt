package fr.airweb.news.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import fr.airweb.news.ui.MainActivity

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}