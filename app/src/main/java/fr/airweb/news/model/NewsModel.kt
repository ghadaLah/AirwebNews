package fr.airweb.news.model

import java.io.Serializable

data class NewsModel(
    val news: List<NewsDetail>
): Serializable {
    data class NewsDetail(
        val nid: Int,
        val type: String,
        val date: String,
        val title: String,
        val picture: String,
        val content: String,
        val dateformated: String
    ): Serializable
}