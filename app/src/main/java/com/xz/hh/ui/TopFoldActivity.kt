package com.xz.hh.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.xz.hh.BaseActivity
import com.xz.hh.R
import com.xz.hh.adapter.ItemCell
import com.xz.hh.adapter.StableAdapter
import com.xz.hh.cells.WanCell
import com.xz.hh.ktx.*
import com.xz.hh.vm.WanViewModel
import kotlinx.android.synthetic.main.activity_top_fold.*
import kotlin.math.abs

/**
 * 顶部折叠布局
 */
class TopFoldActivity : BaseActivity() {
    private val mWanViewModel by lazy {
        viewModels<WanViewModel>().value
    }
    private lateinit var mAdapter: StableAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_fold)
        toolbar()
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            toolbarTitle(if (abs(verticalOffset) >= appBarLayout.totalScrollRange) getString(R.string.coordinator_layout) else "")
        })
        val image = ImageLoader(this)
        image.display(
            top_fold,
            "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1599469764&di=c9bf500e5f3e56c3a3002d1c11758263&src=http://a4.att.hudong.com/22/59/19300001325156131228593878903.jpg"
        )
        mAdapter = createStableAdapter {
            imageLoader = ImageLoader(this@TopFoldActivity)
            onSimpleCallback { position ->
                val itemCell = mAdapter.currentList()[position] as WanCell
                toast(itemCell.listResponse.toString())
            }
        }
        recyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@TopFoldActivity)
        }
    }

    override fun viewDrawn() {
        mWanViewModel.apply {
            wanListRequest()
            subscribeList().applyResponse(this@TopFoldActivity) {
                onSuccess {
                    val itemCellList = mutableListOf<ItemCell>()
                    it.forEach { res ->
                        itemCellList.add(WanCell(res))
                    }
                    mAdapter.submitList(itemCellList)
                }
                onFail { errorCode, message ->
                    toast("ErrorCode:$errorCode ErrorMsg:$message")
                }
            }
        }
    }
}