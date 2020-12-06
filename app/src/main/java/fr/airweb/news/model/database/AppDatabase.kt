package fr.airweb.news.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.airweb.news.model.NewsDao
import fr.airweb.news.model.NewsModel

@Database(entities = [NewsModel.NewsDetail::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun NewsDao(): NewsDao
}