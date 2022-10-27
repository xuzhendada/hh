package com.hi.main.ui

import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hi.common.BaseActivity
import com.hi.common.adapter.StableAdapter
import com.hi.common.data.handleResult
import com.hi.common.ktx.*
import com.hi.main.R
import com.hi.main.cells.ArticleCell
import com.hi.main.databinding.ActivitySmartRefreshBinding
import com.hi.main.vm.HhHiltViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author : wbxuzhen
 * @date : 2020/11/9 13:56
 * @description :
 */
@AndroidEntryPoint
class SmartRefreshActivity : BaseActivity<ActivitySmartRefreshBinding>() {
    private val hiltViewModel: HhHiltViewModel by viewModels()
    private lateinit var stableAdapter: StableAdapter
    private var page = 1

    override fun init() {
        toolbar(getString(R.string.smart_refresh_layout))
        subscribeHomeArticle()
        stableAdapter = createStableAdapter {
            imageLoader = ImageLoader(this@SmartRefreshActivity)
            onSimpleCallback { position ->
                when (val itemCell = stableAdapter.currentList()[position]) {
                    is ArticleCell -> toast(itemCell.articleResponse.author)
                }
            }
        }

        bind.smartRefresh.setOnLoadMoreListener {
            page++
            request()
        }
        bind.smartRefresh.setOnRefreshListener {
            page = 1
            request()
        }
        bind.recycler.apply {
            layoutManager = LinearLayoutManager(this@SmartRefreshActivity)
            adapter = stableAdapter
        }
        bind.recycler.downRefresh()
    }

    private fun subscribeHomeArticle() {
        hiltViewModel.homeArticleLiveData.observe(this) {
            it.handleResult {
                onSuccess { data ->
                    val temp = mutableListOf<ArticleCell>()
                    data.data.datas.forEach { article ->
                        temp.add(ArticleCell(article))
                    }
                    if (temp.isNotEmpty()) {
                        stableAdapter.submitList(data.data.curPage, temp)
                        bind.recycler.refreshSuccess(data.data.curPage <= data.data.pageCount)
                    }
                }
                onFailure {
                }
            }
        }
    }

    private fun request() {
        hiltViewModel.getHomeArticle(pageIndex = page)
    }
}