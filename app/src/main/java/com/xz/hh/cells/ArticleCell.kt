package com.xz.hh.cells

import android.util.Log
import android.view.View
import com.xz.hh.R
import com.xz.hh.adapter.ItemCell
import com.xz.hh.adapter.RecyclerSupport
import com.xz.hh.adapter.RecyclerVH
import com.xz.hh.data.response.ArticleResponse
import com.xz.hh.ktx.debounceClick
import kotlinx.android.synthetic.main.item_article.view.*

class ArticleCell(val articleResponse: ArticleResponse) : ItemCell {
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