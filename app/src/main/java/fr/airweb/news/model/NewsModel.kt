package fr.airweb.news.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class NewsModel(
    val news: List<NewsDetail>
): Serializable {
    @Entity
    data class NewsDetail(
        @field:PrimaryKey
        val nid: Int,
        val type: String,
        val date: String,
        val title: String,
        val picture: String,
        val content: String,
        val dateformated: String
    ): Serializable
}