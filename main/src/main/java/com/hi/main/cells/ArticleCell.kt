package com.hi.main.cells

import android.util.Log
import android.view.View
import com.hi.common.adapter.ItemCell
import com.hi.common.adapter.RecyclerSupport
import com.hi.common.adapter.RecyclerVH
import com.hi.common.data.response.Article
import com.hi.common.ktx.debounceClick
import com.hi.main.R
import kotlinx.android.synthetic.main.item_article.view.*

class ArticleCell(val articleResponse: Article) : ItemCell {
    override fun layoutId() = R.layout.item_article

    override fun itemId() = "${articleResponse.id}"

    override fun itemContent() = "${articleResponse.userId}_${articleResponse.title}"

    override fun onCreateViewHolder(itemView: View, support: RecyclerSupport) =
        ArticleVH(itemView, support)
}

class ArticleVH(itemView: View, support: RecyclerSupport) : RecyclerVH(itemView, support) {
    init {
        itemView.debounceClick {
            support.simpleCallback?.invoke(layoutPosition)
        }
    }

    override fun bind(itemCell: ItemCell, payloads: MutableList<Any>) {
        super.bind(itemCell, payloads)
        if (itemCell is ArticleCell) {
            itemView.article.text = itemCell.articleResponse.title
            Log.e("TAG", itemCell.articleResponse.title)
        }
    }
}