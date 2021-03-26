package com.hi.main.ui

import android.util.Log
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
import com.hi.main.cells.ArticleCell
import com.hi.main.cells.ImgCell
import com.hi.main.cells.WanCell
import com.hi.main.databinding.ActivityTopFoldBinding
import com.hi.main.vm.HiltViewModel
import com.hi.main.vm.WanViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_top_fold.*
import kotlin.math.abs

/**
 * 顶部折叠布局
 */
@AndroidEntryPoint
class TopFoldActivity : BaseActivity<ActivityTopFoldBinding>() {

    private lateinit var mAdapter: StableAdapter
    private lateinit var viewPageAdapter: StableAdapter

    private val hiltViewModel: HiltViewModel by viewModels()

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
        viewPager.apply {
            adapter = viewPageAdapter
        }
        recyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@TopFoldActivity)
        }

    }

    override fun viewDrawn() {
        hiltViewModel.apply {
            getBanner().handleResult(this@TopFoldActivity) {
                onSuccess {
                    val imgCellList = mutableListOf<ImgCell>()
                    it.data.forEach { banner ->
                        imgCellList.add(ImgCell(banner))
                    }
                    viewPageAdapter.submitList(imgCellList)
                }
                onFailure { e ->
                    Log.d("xuzhen", "$e")
                }
            }
            getArticle().handleResult(this@TopFoldActivity) {
                onSuccess {
                    val itemCellList = mutableListOf<ItemCell>()
                    it.data.forEach { listResponse ->
                        itemCellList.add(WanCell(listResponse))
                    }
                    mAdapter.submitList(itemCellList)
                }
                onFailure { e ->
                    Log.d("xuzhen", "$e")
                }
            }
        }
    }
}