package com.hi.main.ui

import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hi.common.BaseActivity
import com.hi.common.adapter.StableAdapter
import com.hi.common.data.handleResult
import com.hi.common.ktx.*
import com.hi.main.R
import com.hi.main.cells.ArticleCell
import com.hi.main.vm.HiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_smart_refresh.*

/**
 * @author : wbxuzhen
 * @date : 2020/11/9 13:56
 * @description :
 */
@AndroidEntryPoint
class SmartRefreshActivity : BaseActivity() {

    private val hiltViewModel: HiltViewModel by viewModels()
    private lateinit var stableAdapter: StableAdapter
    private var page = 1

    override fun layoutId() = R.layout.activity_smart_refresh

    override fun init() {
        toolbar(getString(R.string.smart_refresh_layout))
        stableAdapter = createStableAdapter {
            imageLoader = ImageLoader(this@SmartRefreshActivity)
            onSimpleCallback { position ->
                when (val itemCell = stableAdapter.currentList()[position]) {
                    is ArticleCell -> toast(itemCell.articleResponse.author)
                }
            }
        }

        smartRefresh.setOnLoadMoreListener {
            page++
            request()
        }
        smartRefresh.setOnRefreshListener {
            page = 1
            request()
        }
        recycler.apply {
            layoutManager = LinearLayoutManager(this@SmartRefreshActivity)
            adapter = stableAdapter
        }
        recycler.downRefresh()
    }

    private fun request() {
        hiltViewModel.getHomeArticle(pageIndex = page).handleResult(this) {
            onSuccess {
                val temp = mutableListOf<ArticleCell>()
                it.data.datas.forEach { article ->
                    temp.add(ArticleCell(article))
                }
                if (temp.isNotEmpty()) {
                    stableAdapter.submitList(it.data.curPage, temp)
                    recycler.refreshSuccess(it.data.curPage <= it.data.pageCount)
                }
            }
            onFailure {
            }
        }
    }
}