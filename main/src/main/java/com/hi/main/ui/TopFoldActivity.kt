package com.hi.main.ui

import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.appbar.AppBarLayout
import com.hi.common.BaseActivity
import com.hi.common.adapter.ItemCell
import com.hi.common.adapter.StableAdapter
import com.hi.common.constant.RouterPath
import com.hi.common.data.WanResponse
import com.hi.common.data.handleResult
import com.hi.common.data.response.Banner
import com.hi.common.data.response.ListResponse
import com.hi.common.ktx.*
import com.hi.main.R
import com.hi.main.cells.ImgCell
import com.hi.main.cells.WanCell
import com.hi.main.vm.HiltViewModel
import com.hi.main.vm.WanViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_top_fold.*
import kotlin.math.abs

/**
 * 顶部折叠布局
 */
@AndroidEntryPoint
class TopFoldActivity : BaseActivity() {

    private lateinit var mAdapter: StableAdapter
    private lateinit var viewPageAdapter: StableAdapter

    private val hiltViewModel: HiltViewModel by viewModels()

    override fun layoutId() = R.layout.activity_top_fold

    override fun init() {
        toolbar()
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            toolbarTitle(if (abs(verticalOffset) >= appBarLayout.totalScrollRange) getString(R.string.coordinator_layout) else "")
        })
        mAdapter = createStableAdapter {
            onSimpleCallback { position ->
                val itemCell = mAdapter.currentList()[position] as WanCell
                toast(itemCell.listResponse.toString())
            }
        }
        viewPageAdapter = createStableAdapter {
            imageLoader = ImageLoader(this@TopFoldActivity)
        }
        recyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@TopFoldActivity)
        }

    }

    override fun viewDrawn() {
        hiltViewModel.getArticleAndBanner().handleResult(this) {
            onSuccess {
                it.sameAs<List<ListResponse>> { result ->
                    val itemCellList = mutableListOf<ItemCell>()
                    result.forEach { res ->
                        itemCellList.add(WanCell(res))
                    }
                    mAdapter.submitList(itemCellList)
                }
                it.sameAs<List<Banner>> { result ->
                    val imgCellList = mutableListOf<ImgCell>()
                    result.forEach { banner ->
                        imgCellList.add(ImgCell(banner))
                    }
                    viewPageAdapter.submitList(imgCellList)
                }
                onFailure {
                }
            }
        }
    }
}