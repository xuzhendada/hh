package com.hi.common.ktx

import com.hi.common.adapter.RecyclerSupport
import com.hi.common.adapter.StableAdapter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import androidx.annotation.IntRange
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * 下拉刷新
 */
fun RecyclerView.downRefresh(@IntRange(from = 1) target: Int = 3) {
    when (val layoutManager = this.layoutManager) {
        is LinearLayoutManager -> {
            val firstVisibleItemPosition =
                layoutManager.findFirstVisibleItemPosition()
            if (firstVisibleItemPosition != RecyclerView.NO_POSITION) {
                if (firstVisibleItemPosition > target) {
                    layoutManager.scrollToPosition(target)
                }
                smoothScrollToPosition(0)
            }
            val parent = this.parent
            if (parent is SmartRefreshLayout) {
                parent.autoRefresh()
            }
        }
        is GridLayoutManager -> {
            val firstVisibleItemPosition =
                layoutManager.findFirstVisibleItemPosition()
            if (firstVisibleItemPosition != RecyclerView.NO_POSITION) {
                if (firstVisibleItemPosition > target) {
                    layoutManager.scrollToPosition(target)
                }
                smoothScrollToPosition(0)
            }
            val parent = this.parent
            if (parent is SmartRefreshLayout) {
                parent.autoRefresh()
            }
        }
    }
}

/**
 * 下拉刷新or加載更多完成
 */
fun RecyclerView.refreshSuccess(hasNextPage: Boolean = true) {
    val parent = this.parent
    if (parent is SmartRefreshLayout) {
        when (parent.state) {
            RefreshState.Refreshing -> {
                if (hasNextPage) {
                    parent.finishRefresh()
                } else {
                    parent.finishLoadMoreWithNoMoreData()
                }
            }
            RefreshState.Loading -> {
                if (hasNextPage) {
                    parent.finishLoadMore()
                } else {
                    parent.finishLoadMoreWithNoMoreData()
                }
            }
            else -> {
                //do nothing
            }
        }
    }
}

/**
 * 刷新失败
 *
 * @param callback 回调-1：表示pageIndex>1
 */
fun RecyclerView.refreshFailure(callback: (Int) -> Unit) {
    val parent = this.parent
    if (parent is SmartRefreshLayout) {
        when (parent.state) {
            RefreshState.Refreshing -> {
                callback.invoke(0)
                parent.finishRefresh(false)
            }
            RefreshState.Loading -> {
                callback.invoke(-1)
                parent.finishLoadMore(false)
            }
            else -> {
                //do nothing
            }
        }

    }
}

fun createStableAdapter(support: RecyclerSupport.() -> Unit): StableAdapter {
    return StableAdapter(RecyclerSupport().apply(support))
}