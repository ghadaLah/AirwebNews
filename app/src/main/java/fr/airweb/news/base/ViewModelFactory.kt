package fr.airweb.news.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import fr.airweb.news.model.database.AppDatabase
import fr.airweb.news.ui.NewsListViewModel
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(private val activity: AppCompatActivity): ViewModelProvider.Factory {
    //private val creators: Map<Class<out ViewModel>, Provider<ViewModel>>? = null

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NewsListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "news").build()
            @Suppress("UNCHECKED_CAST")
            return NewsListViewModel(db.NewsDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}