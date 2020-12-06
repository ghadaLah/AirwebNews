package fr.airweb.news.di

import androidx.lifecycle.AndroidViewModel
import dagger.Component
import dagger.android.AndroidInjectionModule
import fr.airweb.news.ui.NewsListViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ApplicationModule::class,
    MainActivityModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        //@BindsInstance
        fun application(application: ApplicationModule): Builder
        fun Build(): AppComponent
    }

    fun inject(listViewModel: NewsListViewModel)
    fun inject(androidViewModel: AndroidViewModel)
}