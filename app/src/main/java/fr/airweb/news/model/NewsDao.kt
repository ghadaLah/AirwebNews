package fr.airweb.news.model

import androidx.room.Dao
import androidx.room.Query

@Dao
interface NewsDao {
    @get: Query("SELECT * FROM NewsDetail GROUP BY type,title")
    val getAll: NewsModel.NewsDetail
}