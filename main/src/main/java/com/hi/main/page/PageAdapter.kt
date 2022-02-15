package com.hi.main.page

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.*
import com.hi.common.data.response.Article
import com.hi.main.R

class PageAdapter :
    PagingDataAdapter<Article, ArticleViewHolder>(
        POST_COMPARATOR
    ) {

    companion object {
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.userId == newItem.userId
            }
        }
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.tvName.text = getItem(position)?.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
        ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article,
                parent,
                false
            )
        )
}

class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvName: TextView = itemView.findViewById(R.id.article)
}
