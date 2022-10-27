package com.hi.main.ui

import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.hi.common.BaseActivity
import com.hi.common.constant.RouterPath
import com.hi.common.ktx.dimen
import com.hi.common.ktx.toolbar
import com.hi.common.widget.UniversalItemDecoration
import com.hi.main.page.PageAdapter
import com.hi.main.R
import com.hi.main.databinding.ActivityPageRecyclerBinding
import com.hi.main.vm.PagingViewModel

/**
 * jetPack->paging
 */
@Route(path = RouterPath.PAGE_RECYCLER)
class PageRecyclerActivity : BaseActivity<ActivityPageRecyclerBinding>() {
    private val mPagingViewMode by lazy {
        viewModels<PagingViewModel>().value
    }

    private val mAdapter: PageAdapter by lazy { PageAdapter() }

    override fun init() {
        toolbar(R.string.page_layout)
        bind.pageRecycler.apply {
            layoutManager = LinearLayoutManager(this@PageRecyclerActivity)
            adapter = mAdapter
            addItemDecoration(
                UniversalItemDecoration(
                    this@PageRecyclerActivity,
                    dimen(R.dimen.page_margin_left),
                    dimen(R.dimen.page_margin_right)
                )
            )
        }
        mPagingViewMode.getArticleData().observe(this, Observer {
            lifecycleScope.launchWhenCreated {
                mAdapter.submitData(it)
            }
        })
    }
}