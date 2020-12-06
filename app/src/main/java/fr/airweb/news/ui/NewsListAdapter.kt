package fr.airweb.news.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.airweb.news.R
import fr.airweb.news.model.NewsModel
import fr.airweb.news.model.NewsModel.*
import kotlinx.android.synthetic.main.news_item.view.*
import javax.inject.Inject

class NewsListAdapter @Inject constructor(): RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder>() {
    private var newsList = mutableListOf<NewsDetail>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsListViewHolder(view)
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    fun addAllNews(news: List<NewsDetail>) {
        newsList.clear()
        newsList.addAll(news)
        notifyDataSetChanged()
    }

    class NewsListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(newsModel: NewsDetail) {
            Glide.with(itemView).load(newsModel.picture).into(itemView.newsImg)
            itemView.newsTitle.text = newsModel.title
            itemView.newsContent.text = newsModel.content
        }
    }
}