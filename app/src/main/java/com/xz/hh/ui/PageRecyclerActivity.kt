package com.xz.hh.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.xz.hh.BaseActivity
import com.xz.hh.R
import com.xz.hh.cells.ArticleCell
import com.xz.hh.ktx.ImageLoader
import com.xz.hh.ktx.createPageAdapter
import com.xz.hh.paging.PageAdapter
import com.xz.hh.vm.WanViewModel
import kotlinx.android.synthetic.main.activity_page_recycler.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PageRecyclerActivity : BaseActivity() {
    private val mWanViewModel by lazy {
        ViewModelProvider(this).get(WanViewModel::class.java)
    }
    private lateinit var mPageAdapter: PageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_recycler)
        mPageAdapter = createPageAdapter {
            imageLoader = ImageLoader(this@PageRecyclerActivity)
        }
        page_recycler.apply {
            adapter = mPageAdapter
            layoutManager = LinearLayoutManager(this@PageRecyclerActivity)
        }
        lifecycleScope.launch {
            mWanViewModel.pager.collect { response ->
                mPageAdapter.submitData(response.map {
                    ArticleCell(it)
                })
            }
        }
    }
}