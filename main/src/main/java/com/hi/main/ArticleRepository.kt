package com.hi.main

import androidx.paging.Pager
import androidx.paging.PagingConfig

    class ArticleRepository {
        fun getArticle() = Pager(PagingConfig(20)) {
        PageSource()
    }.flow
}