package com.hi.main.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hi.main.page.ArticleRepository

class PagingViewModel : ViewModel() {
    private val repository by lazy { ArticleRepository() }

    fun getArticleData() = repository.getArticle().asLiveData()

}