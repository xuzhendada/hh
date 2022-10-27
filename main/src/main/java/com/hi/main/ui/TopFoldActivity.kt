package com.hi.main.ui

import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.hi.common.BaseActivity
import com.hi.common.adapter.ItemCell
import com.hi.common.adapter.StableAdapter
import com.hi.common.data.handleResult
import com.hi.common.ktx.*
import com.hi.main.R
import com.hi.main.cells.ImgCell
import com.hi.main.cells.WanCell
import com.hi.main.databinding.ActivityTopFoldBinding
import com.hi.main.vm.HhHiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

/**
 * 顶部折叠布局
 */
@AndroidEntryPoint
class TopFoldActivity : BaseActivity<ActivityTopFoldBinding>() {

    private lateinit var mAdapter: StableAdapter
    private lateinit var viewPageAdapter: StableAdapter

    private val hiltViewModel: HhHiltViewModel by viewModels()

    override fun init() {
        toolbar()
        bind.appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
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
        bind.viewPager.apply {
            adapter = viewPageAdapter
        }
        bind.recyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@TopFoldActivity)
        }

    }

    override fun viewDrawn() {
        hiltViewModel.apply {
            getBanner()
            getArticle()
            bannerData.observe(this@TopFoldActivity) {
                it.handleResult {
                    onSuccess {
                        val imgCellList = mutableListOf<ImgCell>()
                        it.data.forEach { banner ->
                            imgCellList.add(ImgCell(banner))
                        }
                        viewPageAdapter.submitList(imgCellList)
                    }
                    onFailure { e ->
                        Log.d("TAG", "$e")
                    }
                }
            }
            articleLiveData.observe(this@TopFoldActivity) {
                it.handleResult {
                    onSuccess {
                        val itemCellList = mutableListOf<ItemCell>()
                        it.data.forEach { listResponse ->
                            itemCellList.add(WanCell(listResponse))
                        }
                        mAdapter.submitList(itemCellList)
                    }
                    onFailure { e ->
                        Log.d("TAG", "$e")
                    }
                }
            }
        }
    }
}