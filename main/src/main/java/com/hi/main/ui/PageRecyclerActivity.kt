package com.hi.main.ui

import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.hi.common.BaseActivity
import com.hi.common.constant.RouterPath
import com.hi.common.ktx.createPageAdapter
import com.hi.common.ktx.toolbar
import com.hi.common.paging.PageAdapter
import com.hi.main.R
import com.hi.main.vm.PagingViewModel
import kotlinx.android.synthetic.main.activity_page_recycler.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

/**
 * jetPack->paging
 */
@Route(path = RouterPath.PAGE_RECYCLER)
class PageRecyclerActivity : BaseActivity() {
    private val mPagingViewMode by lazy {
        viewModels<PagingViewModel>().value
    }
    private lateinit var adapter: PageAdapter

    override fun layoutId() = R.layout.activity_page_recycler

    override fun init() {
        toolbar(R.string.page_layout)
        adapter = createPageAdapter {

        }
        page_recycler.apply {
            layoutManager = LinearLayoutManager(this@PageRecyclerActivity)
            adapter = adapter
        }
        mPagingViewMode.getArticleData().observe(this, Observer {
            lifecycleScope.launchWhenCreated {
                adapter.submitData(it)
            }
        })
    }
}